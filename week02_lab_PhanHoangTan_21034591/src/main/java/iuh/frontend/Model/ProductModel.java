package iuh.frontend.Model;

import iuh.backend.enums.ProductStatus;
import iuh.backend.models.Product;
import iuh.backend.models.Productimage;
import iuh.backend.models.Productprice;
import iuh.backend.models.ProductpriceId;
import iuh.backend.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

public class ProductModel {
    private final ProductService productService = new ProductService();

    public void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Extract information from the form
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String unit = request.getParameter("unit");
        String manufacturer = request.getParameter("manufacturer");
        String status = request.getParameter("status");
        String priceStr = request.getParameter("price");
        String imagePath = request.getParameter("imagePath");

        // Validate inputs
        if (name == null || name.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product name is required.");
            return;
        }

        if (priceStr == null || priceStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product price is required.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid price format.");
            return;
        }

        // Create Product object
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setUnit(unit);
        product.setManufacturerName(manufacturer);
        product.setStatus(ProductStatus.valueOf(status)); // Assuming ProductStatus is an enum

        // Save product
        productService.insertProduct(product);

        // Ensure the product ID is set
        if (product.getId() == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Product ID is not generated.");
            return;
        }

        // Create Productprice object
        ProductpriceId priceId = new ProductpriceId(product.getId(), new Date()); // Use the product ID and current date
        Productprice productPrice = new Productprice(priceId, product, price, null); // Note can be null
        productService.saveProductPrice(productPrice); // Save product price

        // Create Productimage object
        Productimage productImage = new Productimage(product.getId(), imagePath); // Assuming you have a constructor for Productimage
        productService.saveProductImage(productImage); // Save product image

        // Redirect to the product page
        response.sendRedirect("product.jsp");
    }
    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        productService.updateStatus(id, ProductStatus.TERMINATED);
        response.sendRedirect("product.jsp");
    }

    public void activeProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        productService.updateStatus(id, ProductStatus.ACTIVE);
        response.sendRedirect("product.jsp");
    }
}

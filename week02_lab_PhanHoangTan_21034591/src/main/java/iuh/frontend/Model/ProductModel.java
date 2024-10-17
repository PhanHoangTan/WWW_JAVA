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
        String note = request.getParameter("note"); // Extract note for Productprice
        String alternative = request.getParameter("alternative"); // Extract alternative for Productimage

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
        Productprice productPrice = new Productprice(priceId, product, price, note); // Use the extracted note
        productService.saveProductPrice(productPrice); // Save product price

        // Create Productimage object
        Productimage productImage = new Productimage(null, product, imagePath, alternative); // Use the extracted alternative
        productService.saveProductImage(productImage); // Save product image

        // Redirect to the product page
        response.sendRedirect("product.jsp");
    }
    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect("product.jsp");
    }

    public void updateProductStatus(HttpServletRequest request, HttpServletResponse response, ProductStatus status) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        productService.updateStatus(id, status);
        response.sendRedirect("product.jsp");
    }

    public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Extract information from the form
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String unit = request.getParameter("unit");
        String manufacturer = request.getParameter("manufacturer");
        String status = request.getParameter("status");
        String priceStr = request.getParameter("price");
        String imagePath = request.getParameter("imagePath");
        String note = request.getParameter("note"); // Extract note for Productprice
        String alternative = request.getParameter("alternative"); // Extract alternative for Productimage

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
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setUnit(unit);
        product.setManufacturerName(manufacturer);
        product.setStatus(ProductStatus.valueOf(status)); // Assuming ProductStatus is an enum

        // Update product
        productService.updateProduct(product);

        // Create Productprice object
        ProductpriceId priceId = new ProductpriceId(product.getId(), new Date()); // Use the product ID and current date
        Productprice productPrice = new Productprice(priceId, product, price, note); // Use the extracted note
        productService.saveProductPrice(productPrice); // Save product price

        // Create Productimage object
        Productimage productImage = new Productimage(null, product, imagePath, alternative); // Use the extracted alternative
        productService.saveProductImage(productImage); // Save product image

        // Redirect to the product page
        response.sendRedirect("product.jsp");
    }
}

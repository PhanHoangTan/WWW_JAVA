package iuh.backend.services;

import iuh.backend.enums.ProductStatus;
import iuh.backend.models.Product;
import iuh.backend.models.Productimage;
import iuh.backend.models.Productprice;
import iuh.backend.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public List<Product> getAll() {
        return productRepository.getAllProduct();
    }

    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
    }

    public boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }
    public boolean deleteProduct(long id) {

        return productRepository.deleteProduct(id);
    }

    public boolean activeProduct(long id) {
        Optional<Product> op = findById(id);
        if (op.isPresent()) {
            Product product = op.get();
            productRepository.setStatus(product, ProductStatus.ACTIVE);
            return true;
        }
        return false;
    }

    public boolean restProduct(long id) {
        Optional<Product> op = findById(id);
        if (op.isPresent()) {
            Product product = op.get();
            productRepository.setStatus(product, ProductStatus.IN_ACTIVE);
            return true;
        }
        return false;
    }
    public void updateStatus(Long id, ProductStatus status) {
        productRepository.updateStatus(id, status);
    }
    public List<Productimage> getProductImages(long productId) {
        return productRepository.getProductImages(productId);
    }
    public List<Productprice> getProductByPrice(long productId) {
        return productRepository.getProductByPrice(productId);
    }
    public Productimage getRandomProductImage(long productId) {
        List<Productimage> images = getProductImages(productId);
        if (images.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return images.get(random.nextInt(images.size()));
    }

     // New method to save Productprice
    public void saveProductPrice(Productprice productPrice) {
        productRepository.saveProductPrice(productPrice);
    }

    // New method to save Productimage
    public void saveProductImage(Productimage productImage) {
        try {
            productRepository.insertProductImage(productImage); // Assuming you implement this in ProductRepository
        } catch (Exception e) {
            // Handle exception, e.g. log the error
        }
    }

    public void getProductPrice(Long id) {
        productRepository.getProductPrice(id);
    }

    public void getProductImage(Long id) {
         productRepository.getProductImage(id);
    }

    public void getProductPriceNote(Long id) {
         productRepository.getProductPriceNote();
    }

    public void getProductImageAlternative(Long id) {
         productRepository.getProductImageAlternative();
    }

    public Product getProductById(Long productId) {
        return productRepository.getProductById(productId);
    }
}

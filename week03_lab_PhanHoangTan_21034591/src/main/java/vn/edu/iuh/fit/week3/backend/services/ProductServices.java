package vn.edu.iuh.fit.week3.backend.services;

import vn.edu.iuh.fit.week3.backend.entities.Product;
import vn.edu.iuh.fit.week3.backend.repositories.ProductRepository;

import java.util.List;

public class ProductServices {
    private ProductRepository productRepository;

    public ProductServices() throws ClassNotFoundException {
        productRepository = new ProductRepository();
    }

    public List<Product> getAll(){
        return productRepository.getAllProduct();
    }

}

package vn.edu.iuh.fit.week3.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.platform.commons.logging.LoggerFactory;
import vn.edu.iuh.fit.week3.backend.entities.Product;

import java.util.List;
import java.util.logging.Logger;

public class ProductRepository {
    private EntityManager em;
    private EntityTransaction et;

    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(Class.forName(this.getClass().getName()));

    public ProductRepository() throws ClassNotFoundException {
    }

    public List<Product>  getAllProduct() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }


}

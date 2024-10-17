package iuh.backend.repositories;

import iuh.backend.enums.ProductStatus;
import iuh.backend.models.Product;
import iuh.backend.models.Productimage;
import iuh.backend.models.Productprice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private EntityManager em;
    private EntityTransaction et;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductRepository() {
        em = Persistence.createEntityManagerFactory("MariaBD").createEntityManager();
        et = em.getTransaction();
    }

    public void insertProduct(Product product) {
        try {
            et.begin();
            em.persist(product);
            et.commit();
        } catch (Exception e) {
            logger.error("Error inserting product: " + e.getMessage(), e);
            et.rollback();
        }
    }


    public void setStatus(Product product, ProductStatus status) {
        product.setStatus(status);
    }

    public void updateStatus(Long id, ProductStatus status) {
        TypedQuery<Product> query = em.createNamedQuery("Product.findById", Product.class)
                .setParameter("id", id);
        Product product1 = query.getSingleResult();
        product1.setStatus(status);
        try {
            et.begin();
            em.merge(product1);
            et.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            et.rollback();
        }
    }

    public boolean updateProduct(Product product) {
        try {
            et.begin();
            em.merge(product);
            et.commit();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            et.rollback();
            return false;
        }
    }

    public List<Product> getAllProduct() {
        return em.createNamedQuery("Product.getAllProduct", Product.class)
                .getResultList();
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    public List<Productimage> getProductImages(long productId) {
        return em.createNamedQuery("Productimage.findByProduct_Id", Productimage.class)
                .setParameter("id", productId)  // Sửa lại "id" để khớp với tên tham số trong truy vấn
                .getResultList();
    }


    public List<Productprice> getProductByPrice(long productId) {
        String query = "SELECT p FROM Productprice p WHERE p.product.id = :productId";
        return em.createQuery(query, Productprice.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    public void saveProductPrice(Productprice productPrice) {
        try {
            et.begin();
            em.persist(productPrice);
            et.commit();
        } catch (Exception e) {
            logger.error("Error saving product price: " + e.getMessage(), e);
            et.rollback();
        }
    }
    public void insertProductImage(Productimage productImage) {
        try {
            et.begin();
            em.persist(productImage);
            et.commit();
        } catch (Exception e) {
            logger.error("Error inserting product image: " + e.getMessage(), e);
            et.rollback();
        }
    }

    public void close() {
        em.close();
    }

    public boolean deleteProduct(long id) {
        try {
            et.begin();

            // Delete related product prices
            List<Productprice> productPrices = em.createQuery("SELECT p FROM Productprice p WHERE p.product.id = :productId", Productprice.class)
                    .setParameter("productId", id)
                    .getResultList();
            for (Productprice productPrice : productPrices) {
                em.remove(productPrice);
            }

            // Delete related product images
            List<Productimage> productImages = em.createQuery("SELECT pi FROM Productimage pi WHERE pi.product.id = :productId", Productimage.class)
                    .setParameter("productId", id)
                    .getResultList();
            for (Productimage productImage : productImages) {
                em.remove(productImage);
            }

            // Delete the product
            Product product = em.find(Product.class, id);
            em.remove(product);

            et.commit();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            et.rollback();
            return false;
        }
    }

    public void getProductPrice(Long id) {
        try {
            TypedQuery<Productprice> query = em.createQuery("SELECT p FROM Productprice p WHERE p.product.id = :productId", Productprice.class);
            query.setParameter("productId", id);
            List<Productprice> productPrices = query.getResultList();
        } catch (Exception e) {
            logger.error("Error fetching product price: " + e.getMessage(), e);
        }
    }

    public void getProductImage(Long id) {
        try {
            TypedQuery<Productimage> query = em.createQuery("SELECT pi FROM Productimage pi WHERE pi.product.id = :productId", Productimage.class);
            query.setParameter("productId", id);
            List<Productimage> productImages = query.getResultList();
        } catch (Exception e) {
            logger.error("Error fetching product image: " + e.getMessage(), e);
    }
}

    public void getProductPriceNote() {
        try {
            TypedQuery<Productprice> query = em.createQuery("SELECT p FROM Productprice p WHERE p.note IS NOT NULL", Productprice.class);
            List<Productprice> productPrices = query.getResultList();
        } catch (Exception e) {
            logger.error("Error fetching product price note: " + e.getMessage(), e);
    }
    }

    public void getProductImageAlternative() {
        try {
            TypedQuery<Productimage> query = em.createQuery("SELECT pi FROM Productimage pi WHERE pi.alternative IS NOT NULL", Productimage.class);
            List<Productimage> productImages = query.getResultList();
        } catch (Exception e) {
            logger.error("Error fetching product image alternative: " + e.getMessage(), e);
    }
    }

    public Product getProductById(Long productId) {
        return em.find(Product.class, productId);
    }
}

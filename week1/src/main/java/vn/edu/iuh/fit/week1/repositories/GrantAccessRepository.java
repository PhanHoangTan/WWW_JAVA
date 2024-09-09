package vn.edu.iuh.fit.week1.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.week1.entities.GrantAccess;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GrantAccessRepository {
    private final Logger logger = Logger.getLogger(GrantAccessRepository.class.getName());
    private EntityManager em;
    private EntityTransaction trans;

    public GrantAccessRepository() {
        em = Persistence.createEntityManagerFactory("mariaDB").createEntityManager();
        trans = em.getTransaction();
    }

    // Insert GrantAccess record into database
    public void insert(GrantAccess grantAccess) {
        try {
            trans.begin();
            em.persist(grantAccess);  // Add GrantAccess entity to persistence context
            trans.commit();
        } catch (Exception exception) {
            if (trans.isActive()) {
                trans.rollback();  // Rollback transaction if error occurs
            }
            logger.log(Level.SEVERE, "Failed to insert grant access: " + exception.getMessage(), exception);
        }
    }
}

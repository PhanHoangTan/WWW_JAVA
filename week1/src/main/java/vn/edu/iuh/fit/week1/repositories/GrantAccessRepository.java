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
    public boolean insert(GrantAccess grantAccess) {
        boolean success = false;
        try {
            trans.begin();
            em.persist(grantAccess);
            trans.commit();
            success = true;
        } catch (Exception exception) {
            if (trans.isActive()) {
                trans.rollback();
            }
            logger.log(Level.SEVERE, "Failed to insert grant access: " + exception.getMessage(), exception);
        }
        return success;
    }

}

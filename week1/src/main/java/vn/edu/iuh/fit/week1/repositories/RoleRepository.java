package vn.edu.iuh.fit.week1.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.week1.entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RoleRepository {

    private final Logger logger = Logger.getLogger(RoleRepository.class.getName());
    private EntityManager em;
    private EntityTransaction trans;

    // Phương thức để lấy kết nối đến CSDL
    public RoleRepository() {
        em = Persistence.createEntityManagerFactory("mariaDB").createEntityManager();
        trans = em.getTransaction();
    }

    // Phương thức để lấy tất cả các vai trò
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        try {
            trans.begin();
            roles = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
            trans.commit();
        } catch (Exception exception) {
            if (trans.isActive()) {
                trans.rollback();
            }
            logger.severe("Failed to fetch roles: " + exception.getMessage());
        }
        return roles;
    }

    // Phương thức để thêm vai trò mới
    public void addRole(Role role) {
        try {
            trans.begin();
            em.persist(role);
            trans.commit();
        } catch (Exception exception) {
            if (trans.isActive()) {
                trans.rollback();
            }
            logger.severe("Failed to add role: " + exception.getMessage());
        }
    }
}

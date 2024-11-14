package vn.edu.iuh.fit.week1.repositories;

import jakarta.persistence.*;
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
    public Role getRoleByStatus(byte status) {
        try {
            return em.createQuery("SELECT r FROM Role r WHERE r.status = :status", Role.class)
                    .setParameter("status", status)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // or handle it as needed
        }
    }
    // Phương thức để cập nhật vai trò
    public void updateRole(Role role) {
        try {
            trans.begin();
            Role existingRole = em.find(Role.class, role.getRoleId());
            if (existingRole != null) {
                existingRole.setRoleName(role.getRoleName());
                existingRole.setStatus(role.getStatus());
                // Update other fields if needed
            } else {
                logger.warning("Role with ID " + role.getRoleId() + " not found.");
            }
            trans.commit();
        } catch (Exception exception) {
            if (trans.isActive()) {
                trans.rollback();
            }
            logger.severe("Failed to update role: " + exception.getMessage());
        }
    }
    public Role getRoleByRoleName(String roleName) {
        Role role = null;
        try {
            trans.begin();
            // Create a query to find a role by its name
            TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class);
            query.setParameter("roleName", roleName);
            role = query.getSingleResult();
            trans.commit();
        } catch (NoResultException e) {
            // Handle the case where no result is found
            logger.info("No role found with name: " + roleName);
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            logger.severe("Failed to fetch role by name: " + e.getMessage());
        }
        return role;
    }
}

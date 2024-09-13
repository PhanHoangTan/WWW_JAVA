package vn.edu.iuh.fit.week1.repositories;

import jakarta.persistence.*;
import vn.edu.iuh.fit.week1.entities.Account;
import vn.edu.iuh.fit.week1.entities.GrantAccess;
import vn.edu.iuh.fit.week1.entities.GrantAccessId;
import vn.edu.iuh.fit.week1.entities.Role;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GrantAccessRepository {
    private static final Logger logger = Logger.getLogger(GrantAccessRepository.class.getName());
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mariaDB");



    // Method to assign a role to a user
    public boolean assignRolesToUser(String accountId, List<String> roleIds) {
        if (accountId == null || roleIds == null || roleIds.isEmpty()) {
            logger.severe("Account ID or Role IDs are null or empty");
            return false;
        }

        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = entityManagerFactory.createEntityManager();
            trans = em.getTransaction();
            trans.begin();

            // Remove existing GrantAccess records for the account
            Query removeQuery = em.createQuery("DELETE FROM GrantAccess g WHERE g.accountId = :accountId");
            removeQuery.setParameter("accountId", accountId);
            removeQuery.executeUpdate();

            // Add new GrantAccess records for the provided role IDs
            for (String roleId : roleIds) {
                Role role = em.find(Role.class, roleId);
                if (role != null) {
                    GrantAccess grantAccess = new GrantAccess();
                    grantAccess.setAccountId(accountId);
                    grantAccess.setRoleId(roleId);
                    grantAccess.setIsGrant(true);
                    grantAccess.setNote("New note"); // Set default values as needed

                    em.persist(grantAccess);
                } else {
                    logger.warning("Role with ID " + roleId + " not found.");
                }
            }

            trans.commit();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error assigning roles to user", e);
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }




    public List<Role> getRolesByAccountId(String accountId) {
        EntityManager em = null;
        List<Role> roles = null;

        try {
            em = entityManagerFactory.createEntityManager();
            // Define the query to get roles based on the account ID
            String query = "SELECT r FROM Role r JOIN GrantAccess ga ON r.roleId = ga.roleId WHERE ga.accountId = :accountId";
            TypedQuery<Role> typedQuery = em.createQuery(query, Role.class);
            typedQuery.setParameter("accountId", accountId);
            roles = typedQuery.getResultList();
        } catch (Exception e) {
            logger.severe("Error retrieving roles for account ID " + accountId + ": " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return roles;
    }

    public boolean updateGrantAccess(String accountId, String roleId, boolean isGrant, String note) {
        if (accountId == null || roleId == null) {
            logger.severe("Account ID or Role ID is null");
            return false;
        }

        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = entityManagerFactory.createEntityManager();
            trans = em.getTransaction();
            trans.begin();

            // Check if the GrantAccess record exists
            Query query = em.createQuery("SELECT ga FROM GrantAccess ga WHERE ga.accountId = :accountId AND ga.roleId = :roleId");
            query.setParameter("accountId", accountId);
            query.setParameter("roleId", roleId);
            GrantAccess grantAccess = (GrantAccess) query.getResultStream().findFirst().orElse(null);

            if (grantAccess != null) {
                // Update existing record
                grantAccess.setIsGrant(isGrant);
                grantAccess.setNote(note);
                em.merge(grantAccess);
            } else {
                logger.warning("GrantAccess record not found for accountId: " + accountId + " and roleId: " + roleId);
                return false;
            }

            trans.commit();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating GrantAccess record", e);
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


    public boolean updateRoles(String accountId, List<String> roleNames) {
        if (accountId == null || roleNames == null) {
            logger.severe("Account ID or Role Names are null");
            return false;
        }

        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = entityManagerFactory.createEntityManager();
            trans = em.getTransaction();
            trans.begin();

            // Find the account
            Account account = em.find(Account.class, accountId);
            if (account != null) {
                // Log current roles
                List<String> currentRoleNames = account.getGrantAccesses().stream()
                        .map(grantAccess -> grantAccess.getRole().getRoleName())
                        .collect(Collectors.toList());
                logger.info("Current roles: " + currentRoleNames);

                // If the list of roles has changed, update
                if (!currentRoleNames.equals(roleNames)) {
                    // Remove old roles from the database
                    Query removeQuery = em.createQuery("DELETE FROM GrantAccess g WHERE g.accountId = :accountId");
                    removeQuery.setParameter("accountId", accountId);
                    removeQuery.executeUpdate();

                    // Clear roles in memory
                    account.getGrantAccesses().clear();

                    // Add new roles
                    for (String roleName : roleNames) {
                        Role role = em.createNamedQuery("Role.findByRoleName", Role.class)
                                .setParameter("roleName", roleName)
                                .getSingleResult();

                        if (role != null) {
                            GrantAccess grantAccess = new GrantAccess();
                            grantAccess.setAccountId(accountId);
                            grantAccess.setRoleId(role.getRoleId());
                            grantAccess.setIsGrant(true);
                            grantAccess.setNote("New note"); // Set default values as needed

                            grantAccess.setAccount(account);
                            grantAccess.setRole(role);

                            em.persist(grantAccess);
                            account.getGrantAccesses().add(grantAccess);
                        } else {
                            logger.warning("Role with name " + roleName + " not found.");
                        }
                    }
                }

                // Update the account
                em.merge(account);
            } else {
                logger.warning("Account with ID " + accountId + " not found.");
                return false;
            }

            trans.commit();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating roles", e);
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }




}

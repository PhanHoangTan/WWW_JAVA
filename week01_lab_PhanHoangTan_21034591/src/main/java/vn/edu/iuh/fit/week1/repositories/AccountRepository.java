package vn.edu.iuh.fit.week1.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
import vn.edu.iuh.fit.week1.entities.Account;
import vn.edu.iuh.fit.week1.entities.GrantAccess;
import vn.edu.iuh.fit.week1.entities.GrantAccessId;
import vn.edu.iuh.fit.week1.entities.Role;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AccountRepository {
    private final Logger logger = Logger.getLogger(AccountRepository.class.getName());
    @Inject
    private EntityManager em;
    @Inject
    private EntityTransaction trans;

    @Inject
    private RoleRepository roleRepository;
    @Inject
    private GrantAccessRepository grantAccessRepository;

    public AccountRepository() {
        em = Persistence.createEntityManagerFactory("mariaDB").createEntityManager();
        trans = em.getTransaction();
        this.roleRepository = new RoleRepository();  // Initialize as needed
        this.grantAccessRepository = new GrantAccessRepository();  // Initialize as needed
    }

    // Insert account and grant default access to all roles with status false
    public boolean insert(Account account) {
        boolean success = false;
        try {
            trans.begin();

            // Hash the password before saving it to the database
            em.persist(account);

            trans.commit();  // Commit the transaction after inserting the Account
            success = true;
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            logger.log(Level.SEVERE, "Failed to insert account: " + e.getMessage(), e);
        }
        return success;
    }



    // Update an existing account



    // Update the status of an account
    public boolean updateStatus(String accountId, int status) {
        try {
            trans.begin();
            Account acc = em.find(Account.class, accountId);
            if (acc != null) {
                acc.setStatus((byte) status);
                em.merge(acc);  // Ensure the change is persisted
                trans.commit();
                return true;
            }
        } catch (Exception exception) {
            if (trans.isActive()) {
                trans.rollback();
            }
            logger.log(Level.SEVERE, "Failed to update status: " + exception.getMessage(), exception);
        }
        return false;
    }

    // Logon method to validate credentials
    public Optional<Account> logon(String id, String psw) {
        try {
            TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.accountId = :id AND a.password = :psw", Account.class);
            query.setParameter("id", id);
            query.setParameter("psw", psw);
            return query.getResultList().stream().findFirst();
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Logon failed: " + exception.getMessage(), exception);
            return Optional.empty();
        }
    }

    // Fetch all accounts
    public List<Account> getAllAccounts() {
        try {
            TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a", Account.class);
            return query.getResultList();
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Failed to fetch accounts: " + exception.getMessage(), exception);
            return null;
        }
    }

    public boolean validateLogin(String username, String password) {
        try {
            TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.accountId = :username AND a.password = :password", Account.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getResultList().size() > 0;
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Failed to validate login: " + exception.getMessage(), exception);
            return false;
        }
    }
    public boolean deleteAccount(String accountId) {
        try {
            trans.begin();
            Account account = em.find(Account.class, accountId);
            if (account != null) {
                em.remove(account);
                trans.commit();
                return true;
            } else {
                logger.log(Level.WARNING, "Account with ID " + accountId + " not found.");
            }
        } catch (Exception exception) {
            if (trans.isActive()) {
                trans.rollback();
            }
            logger.log(Level.SEVERE, "Failed to delete account: " + exception.getMessage(), exception);
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
        }
        return false;
    }
    public Account getAccountById(String accountId) {
        try {
            return em.find(Account.class, accountId);
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Failed to fetch account by ID: " + exception.getMessage(), exception);
            return null;
        }
    }
    //role lấy từ bảng grantaccess
    public String getRoleIdbyAccountId(String accountId) {
        try {
            TypedQuery<GrantAccess> query = em.createQuery("SELECT g FROM GrantAccess g WHERE g.accountId = :accountId", GrantAccess.class);
            query.setParameter("accountId", accountId);
            return query.getSingleResult().getRoleId();
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Failed to fetch role by account ID: " + exception.getMessage(), exception);
            return null;
        }
    }

    public void updateAccount(Account account) {
        try {
            trans.begin();
            Account existingAccount = em.find(Account.class, account.getAccountId());
            if (existingAccount != null) {
                existingAccount.setFullName(account.getFullName());
                existingAccount.setEmail(account.getEmail());
                existingAccount.setPassword(account.getPassword());
                existingAccount.setPhone(account.getPhone());
                existingAccount.setStatus(account.getStatus());
                em.merge(existingAccount);
                trans.commit();
            }
        } catch (Exception exception) {
            if (trans.isActive()) {
                trans.rollback();
            }
            logger.log(Level.SEVERE, "Failed to update account: " + exception.getMessage(), exception);
        }
    }
    public void updateRoles(String accountId, List<String> roleNames) {
        try {
            em.getTransaction().begin();

            // Tìm kiếm đối tượng Account theo accountId
            Account account = em.find(Account.class, accountId);

            if (account != null) {
                // Log current roles
                System.out.println("Current roles: " + account.getGrantAccesses().stream()
                        .map(grantAccess -> grantAccess.getRole().getRoleName())
                        .collect(Collectors.toList()));

                // Retrieve current roles
                List<String> currentRoleNames = account.getGrantAccesses().stream()
                        .map(grantAccess -> grantAccess.getRole().getRoleName())
                        .collect(Collectors.toList());

                // Nếu danh sách quyền thay đổi, thực hiện cập nhật
                if (!currentRoleNames.equals(roleNames)) {
                    // Xóa các quyền cũ khỏi cơ sở dữ liệu
                    for (GrantAccess grantAccess : account.getGrantAccesses()) {
                        em.remove(grantAccess);
                    }
                    account.getGrantAccesses().clear(); // Xóa khỏi bộ nhớ

                    // Thêm các quyền mới
                    for (String roleName : roleNames) {
                        // Tìm kiếm Role theo roleName
                        Role role = em.createNamedQuery("Role.findByRoleName", Role.class)
                                .setParameter("roleName", roleName)
                                .getSingleResult();

                        if (role != null) {
                            // Tạo GrantAccess mới
                            GrantAccess grantAccess = new GrantAccess();
                            grantAccess.setAccountId(accountId);
                            grantAccess.setRoleId(role.getRoleId());
                            grantAccess.setIsGrant(true);

                            // Thiết lập các liên kết
                            grantAccess.setAccount(account);
                            grantAccess.setRole(role);

                            // Persist GrantAccess và cập nhật Account
                            em.persist(grantAccess);
                            account.getGrantAccesses().add(grantAccess);
                        }
                    }
                }

                // Merge account để cập nhật vào cơ sở dữ liệu
                em.merge(account);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

}

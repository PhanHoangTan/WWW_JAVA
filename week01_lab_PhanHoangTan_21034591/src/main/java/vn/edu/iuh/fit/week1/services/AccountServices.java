package vn.edu.iuh.fit.week1.services;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.week1.entities.Account;

public class AccountServices {

    @Inject
    private EntityManager em;

    public void updateAccountAccountIdByAccountPassword(String password, String newAccountId) {
        // Tìm kiếm tài khoản theo mật khẩu
        Account account = em.createNamedQuery("GrantAccess.findByAccountPassword", Account.class)
                .setParameter("password", password)
                .getSingleResult();

        if (account != null) {
            // Cập nhật thuộc tính accountId
            account.setAccountId(newAccountId);

            // Lưu thay đổi
            em.merge(account);
        }
    }
}

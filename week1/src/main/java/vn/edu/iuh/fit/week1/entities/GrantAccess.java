package vn.edu.iuh.fit.week1.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "grant_access")
@NamedQueries({
        @NamedQuery(name = "GrantAccess.findByAccount_FullNameOrderByAccount_AccountIdAsc", query = "select g from GrantAccess g where g.account.fullName = :fullName order by g.account.accountId"),
        @NamedQuery(name = "GrantAccess.deleteByAccount_Email", query = "delete from GrantAccess g where g.account.email = :email"),
        @NamedQuery(name = "GrantAccess.findByAccountPassword", query = "select g.account from GrantAccess g where g.account.password = :password")
})
public class GrantAccess implements Serializable {

    @Id
    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Id
    @Column(name = "role_id", nullable = false)
    private String roleId;

    @Column(name = "is_grant", nullable = false)
    private Boolean isGrant = false;

    @Column(name = "note", length = 250)
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    // Constructors, getters and setters
    public GrantAccess() {}

    public GrantAccess(String accountId, String roleId, Boolean isGrant, String note) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.isGrant = isGrant;
        this.note = note;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Boolean isGrant) {
        this.isGrant = isGrant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

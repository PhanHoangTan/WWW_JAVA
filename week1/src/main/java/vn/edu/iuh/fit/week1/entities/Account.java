package vn.edu.iuh.fit.week1.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@NamedQueries({
        @NamedQuery(name = "Account.findByFullNameOrderByPasswordAsc", query = "select a from Account a where a.fullName = :fullName order by a.password"),
        @NamedQuery(name = "Account.deleteByPhone", query = "delete from Account a where a.phone = :phone"),
        @NamedQuery(name = "Account.updateAccountIdByPassword", query = "update Account a set a.accountId = :accountId where a.password = :password")

})
public class Account {

    @Id
    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "status", nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "account")
    private Set<GrantAccess> grantAccesses = new LinkedHashSet<>();

    public Set<GrantAccess> getGrantAccesses() {
        return grantAccesses;
    }

    public void setGrantAccesses(Set<GrantAccess> grantAccesses) {
        this.grantAccesses = grantAccesses;
    }

    // Constructor mặc định
    public Account() {
    }

    // Constructor đầy đủ
    public Account(String accountId, String fullName, String password, String email, String phone, Byte status) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }


    // Getter và Setter cho các thuộc tính
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}

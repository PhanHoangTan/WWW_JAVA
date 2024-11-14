package vn.edu.iuh.fit.week1.dtos;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link vn.edu.iuh.fit.week1.entities.GrantAccessId}
 */
public class GrantAccessIdDto implements Serializable {
    private final String roleId;
    private final String accountId;

    public GrantAccessIdDto(String roleId, String accountId) {
        this.roleId = roleId;
        this.accountId = accountId;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantAccessIdDto entity = (GrantAccessIdDto) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.accountId, entity.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, accountId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "roleId = " + roleId + ", " +
                "accountId = " + accountId + ")";
    }
}
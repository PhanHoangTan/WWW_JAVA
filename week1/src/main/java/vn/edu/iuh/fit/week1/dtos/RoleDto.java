package vn.edu.iuh.fit.week1.dtos;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link vn.edu.iuh.fit.week1.entities.Role}
 */
public class RoleDto implements Serializable {
    private final String roleId;
    private final String roleName;
    private final String description;
    private final Byte status;

    public RoleDto(String roleId, String roleName, String description, Byte status) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
        this.status = status;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

    public Byte getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto entity = (RoleDto) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.roleName, entity.roleName) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, description, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "roleId = " + roleId + ", " +
                "roleName = " + roleName + ", " +
                "description = " + description + ", " +
                "status = " + status + ")";
    }
}
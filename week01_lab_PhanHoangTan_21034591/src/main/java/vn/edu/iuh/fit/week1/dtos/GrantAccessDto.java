package vn.edu.iuh.fit.week1.dtos;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link vn.edu.iuh.fit.week1.entities.GrantAccess}
 */
public class GrantAccessDto implements Serializable {
    private final Boolean isGrant;
    private final String note;

    public GrantAccessDto(Boolean isGrant, String note) {
        this.isGrant = isGrant;
        this.note = note;
    }

    public Boolean getIsGrant() {
        return isGrant;
    }

    public String getNote() {
        return note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantAccessDto entity = (GrantAccessDto) o;
        return Objects.equals(this.isGrant, entity.isGrant) &&
                Objects.equals(this.note, entity.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isGrant, note);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "isGrant = " + isGrant + ", " +
                "note = " + note + ")";
    }
}
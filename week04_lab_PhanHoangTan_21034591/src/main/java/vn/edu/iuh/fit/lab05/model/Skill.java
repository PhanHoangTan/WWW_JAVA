package vn.edu.iuh.fit.lab05.model;

public class Skill {
    private Long id;
    private String skill_Name;
    private String description;
    private String field;

    public Skill() {
    }

    public Skill(Long id, String skill_Name, String description, String field) {
        this.id = id;
        this.skill_Name = skill_Name;
        this.description = description;
        this.field = field;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkill_Name() {
        return skill_Name;
    }

    public void setSkill_Name(String skill_Name) {
        this.skill_Name = skill_Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id='" + id + '\'' +
                ", skill_Name='" + skill_Name + '\'' +
                ", description='" + description + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}

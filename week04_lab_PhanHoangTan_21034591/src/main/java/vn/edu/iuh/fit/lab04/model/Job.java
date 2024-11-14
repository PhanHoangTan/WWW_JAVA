package vn.edu.iuh.fit.lab05.model;

public class Job {
    private Long id;
    private String description;

    public Job() {
    }

    public Job(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

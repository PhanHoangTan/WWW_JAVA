package vn.edu.iuh.fit.lab06_accounts.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public @Data class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_name")  // Map to snake case
    private String ownerName;

    private double balance;

    public Account() {
    }

    public Account(Long id, String ownerName, double balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}

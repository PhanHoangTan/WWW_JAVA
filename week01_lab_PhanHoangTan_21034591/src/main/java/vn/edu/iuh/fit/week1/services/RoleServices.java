package vn.edu.iuh.fit.week1.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.week1.entities.Role;
import vn.edu.iuh.fit.week1.repositories.RoleRepository;

public class RoleServices {
    private RoleRepository repository;

    public RoleServices() {
        repository=new RoleRepository();
    }

    public void addRole(Role role){
        repository.addRole(role);
    }
}
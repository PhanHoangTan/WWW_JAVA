package vn.edu.iuh.fit.lab06.frontend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.lab06.frontend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
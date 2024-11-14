package vn.edu.iuh.fit.lab06.frontend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.lab06.frontend.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
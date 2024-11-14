package vn.edu.iuh.fit.lab06.frontend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab06.frontend.repositories.PostRepository;
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
}

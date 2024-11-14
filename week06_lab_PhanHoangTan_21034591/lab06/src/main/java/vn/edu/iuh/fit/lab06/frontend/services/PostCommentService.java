package vn.edu.iuh.fit.lab06.frontend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab06.frontend.models.PostComment;
import vn.edu.iuh.fit.lab06.frontend.repositories.PostCommentRepository;

import java.util.List;

@Service
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;

}

package vn.edu.iuh.fit.lab06.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.lab06.frontend.repositories.PostCommentRepository;

@Controller
@RequestMapping("/post-comments")
public class PostCommentController {

    private final PostCommentRepository postCommentRepository;

    @Autowired
    public PostCommentController(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    @GetMapping("/list")
    public String listPostComments(Model model) {
        model.addAttribute("postComments", postCommentRepository.findAll());
        return "postComments/list-postComments";
    }
}
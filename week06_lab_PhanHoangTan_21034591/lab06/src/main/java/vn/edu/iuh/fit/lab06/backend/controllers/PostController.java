package vn.edu.iuh.fit.lab06.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.lab06.frontend.repositories.PostRepository;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/list")
    public String showPostList(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts/list-posts";
    }
}
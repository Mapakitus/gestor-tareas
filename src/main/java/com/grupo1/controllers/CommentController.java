package com.grupo1.controllers;

import com.grupo1.entities.Comment;
import com.grupo1.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("comments")
public class CommentController {

    private final CommentRepository commentRepository;

    @GetMapping
    public String getAllComments(Model model) {
        model.addAttribute("comments", commentRepository.findAll());
        return "comment/comment-list";
    }

    @PostMapping
    public String saveComment(@ModelAttribute Comment comment) {
        commentRepository.save(comment);
        return "redirect:/comments";
    }
}



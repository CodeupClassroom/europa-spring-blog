package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepo;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.ArrayList;

@Controller
public class PostController {

    private PostRepo postDao;

    public PostController(PostRepo postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable int id, Model model){
        Post post1 = new Post(id, "Europa's First Post", "Remote Learning Today!");
        model.addAttribute("title", post1.getTitle());
        model.addAttribute("body", post1.getBody());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreatePostForm(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "create a new post";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        // delete post
        postDao.deleteById(id);
        return "redirect:/posts";
    }

}

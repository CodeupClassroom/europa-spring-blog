package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepo;
import com.codeup.springblog.repositories.UserRepo;
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
    private UserRepo userDao;

    public PostController(PostRepo postDao, UserRepo userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model){
//        Post post1 = new Post(id, "Europa's First Post", "Remote Learning Today!");
//        model.addAttribute("title", post1.getTitle());
//        model.addAttribute("body", post1.getBody());
        model.addAttribute("post",postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String getCreatePostForm(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String body ){
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        newPost.setUser(userDao.getOne(1l));
        postDao.save(newPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        // delete post
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post p = postDao.getOne(id);
        p.setTitle(title);
        p.setBody(body);
        postDao.save(p);
        return "redirect:/posts";
    }


}

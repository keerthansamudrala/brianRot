package com.keerthan.brainRot.controller;

import com.keerthan.brainRot.model.Post;
import com.keerthan.brainRot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    PostService service;

    @GetMapping("/posts")
    public List<Post> getPosts(){
        return service.getPosts();
    }

    @GetMapping("/posts/{post_id}")
    public Post getPostById(@PathVariable int post_id){
        return service.getPostById(post_id);
    }

    @PostMapping("/posts")
    public void addPost(@RequestBody Post post){
        System.out.println(post);
        service.addPost(post);
    }

    @PutMapping("/posts")
    public void updatePost(@RequestBody Post post){
        service.updatePost(post);
    }

    @DeleteMapping("/posts")
    public void deletePost(@RequestBody Post post){
        service.deletePostById(post.getPost_id());
    }


}

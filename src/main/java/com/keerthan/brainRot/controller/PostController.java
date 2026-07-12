package com.keerthan.brainRot.controller;

import com.keerthan.brainRot.dto.post.PostRequestDTO;
import com.keerthan.brainRot.dto.post.PostResponseDTO;
import com.keerthan.brainRot.model.Post;
import com.keerthan.brainRot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> getPosts(){
        List<PostResponseDTO> list = postService.getPosts();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/posts/{post_id}")
    public Post getPostById(@PathVariable int post_id){
        return postService.getPostById(post_id);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody PostRequestDTO dto){
        Post saved = postService.addPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable int id, @RequestBody PostRequestDTO dto) {
        PostResponseDTO updated = postService.updatePost(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable int id) {
        Post deleted = postService.deletePostById(id);
        return ResponseEntity.ok(deleted);
    }

}

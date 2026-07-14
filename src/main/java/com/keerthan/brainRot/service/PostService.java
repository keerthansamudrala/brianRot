package com.keerthan.brainRot.service;

import com.keerthan.brainRot.dto.post.PostRequestDTO;
import com.keerthan.brainRot.dto.post.PostResponseDTO;
import com.keerthan.brainRot.model.Post;
import com.keerthan.brainRot.model.User;
import com.keerthan.brainRot.repository.PostRepository;
import com.keerthan.brainRot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    public List<PostResponseDTO> getPosts() {
        List<Post> posts = postRepository.findAll();

        List<PostResponseDTO> response = new ArrayList<>();
        for (Post post : posts) {
            PostResponseDTO dto = new PostResponseDTO();
            dto.setPost_id(post.getPost_id());
            dto.setPost_image(post.getPost_image());
            dto.setPost_datetime(post.getPost_datetime());
            dto.setPost_cockroaches(post.getPost_cockroaches());
            dto.setPost_title(post.getPost_title());
            response.add(dto);
        }
        return response;
    }

    public Post getPostById(int post_id) {
        return postRepository.findById(post_id).orElse(null);
    }

    @Transactional
    public Post addPost(PostRequestDTO dto) {
        User user = userRepository.findById(dto.getUser_id())
                .orElseThrow(() -> new RuntimeException("User with ID " + dto.getUser_id() + " not found."));

        if (LocalDate.now().isAfter(user.getLastRefillDate())) {
            user.setCockroaches_left(100);
            user.setLastRefillDate(LocalDate.now());
        }

        int postCost = 2;
        if (user.getCockroaches_left() < postCost) {
            throw new RuntimeException("You need at least 2 cockroaches to add a post. Current balance: " + user.getCockroaches_left());
        }

        user.setCockroaches_left(user.getCockroaches_left() - postCost);
        user.setTotal_cockroaches_spent(user.getTotal_cockroaches_spent() + postCost);

        userRepository.save(user);

        Post post = new Post();
        post.setPost_title(dto.getPost_title());
        post.setPost_image(dto.getPost_image());
        post.setUser_id(dto.getUser_id());

        return postRepository.save(post);
    }

    public PostResponseDTO updatePost(int id, PostRequestDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no post is available with post_id " + id));

        post.setPost_title(dto.getPost_title());
        post.setPost_image(dto.getPost_image());

        Post saved = postRepository.save(post);

        PostResponseDTO response = new PostResponseDTO();
        response.setPost_title(saved.getPost_title());
        response.setPost_image(saved.getPost_image());
        response.setPost_datetime(saved.getPost_datetime());
        response.setPost_cockroaches(saved.getPost_cockroaches());

        return response;
    }

    public Post deletePostById(int id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.deleteById(id);
        return post;
    }

}

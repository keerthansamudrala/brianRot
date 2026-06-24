package com.keerthan.brainRot.service;

import com.keerthan.brainRot.dto.PostRequestDTO;
import com.keerthan.brainRot.dto.PostResponseDTO;
import com.keerthan.brainRot.model.Post;
import com.keerthan.brainRot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository  postRepository;



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

    public Post addPost(PostRequestDTO dto) {
        Post post = new Post();
        post.setPost_title(dto.getPost_title());
        post.setPost_image(dto.getPost_image());
        post.setUser_id(dto.getUser_id());

        return postRepository.save(post);

    }

    public PostResponseDTO updatePost(int id, PostRequestDTO dto) {
        Post post = postRepository.findById(id).orElseThrow();

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

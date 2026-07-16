package com.keerthan.brainRot.service;

import com.keerthan.brainRot.dto.post.PostRequestDTO;
import com.keerthan.brainRot.dto.post.PostResponseDTO;
import com.keerthan.brainRot.model.Post;
import com.keerthan.brainRot.model.User;
import com.keerthan.brainRot.repository.PostRepository;
import com.keerthan.brainRot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            dto.setPostId((post.getPostId()));
            dto.setPostImage(post.getPostImage());
            dto.setPostDatetime(post.getPostDatetime());
            dto.setPostCockroaches(post.getPostCockroaches());
            dto.setPostTitle(post.getPostTitle());
            dto.setUserId(post.getUserId());
            response.add(dto);
        }
        return response;
    }

    public Post getPostById(int post_id) {
        return postRepository.findById(post_id).orElse(null);
    }

    @Transactional
    public Post addPost(PostRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User with ID " + dto.getUserId() + " not found."));

        if (LocalDate.now().isAfter(user.getLastRefillDate())) {
            user.setCockroachesLeft((100));
            user.setLastRefillDate(LocalDate.now());
        }

        int postCost = 2;
        if (user.getCockroachesLeft() < postCost) {
            throw new RuntimeException("You need at least 2 cockroaches to add a post. Current balance: " + user.getCockroachesLeft());
        }

        user.setCockroachesLeft((user.getCockroachesLeft() - postCost));
        user.setTotalCockroachesSpent((user.getTotalCockroachesSpent() + postCost));

        userRepository.save(user);

        Post post = new Post();
        post.setPostTitle((dto.getPostTitle()));
        post.setPostImage((dto.getPostImage()));
        post.setUserId((dto.getUserId()));

        return postRepository.save(post);
    }

    public PostResponseDTO updatePost(int id, PostRequestDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no post is available with post_id " + id));

        post.setPostTitle((dto.getPostTitle()));
        post.setPostImage((dto.getPostImage()));

        Post saved = postRepository.save(post);

        PostResponseDTO response = new PostResponseDTO();
        response.setPostTitle((saved.getPostTitle()));
        response.setPostImage((saved.getPostImage()));
        response.setPostDatetime((saved.getPostDatetime()));
        response.setPostCockroaches((saved.getPostCockroaches()));

        return response;
    }

    public Post deletePostById(int id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.deleteById(id);
        return post;
    }

}

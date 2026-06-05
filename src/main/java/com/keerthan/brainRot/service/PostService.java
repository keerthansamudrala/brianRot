package com.keerthan.brainRot.service;

import com.keerthan.brainRot.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    List<Post> posts = new ArrayList<>(Arrays.asList (
            new Post(101, "Hole in the wall", 99),
            new Post(102, "house on fire",147),
            new Post(103, "A day at FormulaOne", 200)
    ));

    public  List<Post> getPosts() {
        return posts;
    }

    public Post getPostById(int post_id) {
        return posts.stream()
                .filter(p -> p.getPost_id() == post_id)
                .findFirst().get();
    }

    public void addPost(Post post) {
        posts.add(post);
    }


}

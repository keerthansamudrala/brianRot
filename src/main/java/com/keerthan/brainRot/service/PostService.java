package com.keerthan.brainRot.service;

import com.keerthan.brainRot.model.Post;
import com.keerthan.brainRot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository post_repo;

//    List<Post> posts = new ArrayList<>(Arrays.asList (
//            new Post(101, "Hole in the wall", 99),
//            new Post(102, "house on fire",147),
//            new Post(103, "A day at FormulaOne", 200)
//    ));

    public  List<Post> getPosts() {
        return post_repo.findAll();
    }

    public Post getPostById(int post_id) {
        return post_repo.findById(post_id).orElse(null);
    }

    public void addPost(Post post) {
        post_repo.save(post);
    }

    public void updatePost(Post post) {
        post_repo.save(post);
    }

    public void deletePostById(int post_id) {
        post_repo.deleteById(post_id);
    }


}

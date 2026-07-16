package com.keerthan.brainRot.repository;

import com.keerthan.brainRot.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Transactional
    void deleteByUserId(int userId);
}

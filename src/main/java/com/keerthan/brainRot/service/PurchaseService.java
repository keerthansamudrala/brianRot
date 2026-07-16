package com.keerthan.brainRot.service;

import com.keerthan.brainRot.dto.purchase.PurchaseRequestDTO;
import com.keerthan.brainRot.model.Post;
import com.keerthan.brainRot.model.Purchase;
import com.keerthan.brainRot.model.User;
import com.keerthan.brainRot.repository.PostRepository;
import com.keerthan.brainRot.repository.PurchaseRepository;
import com.keerthan.brainRot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public String buyPost(PurchaseRequestDTO dto) {
        int userId = dto.getUserId();
        int postId = dto.getPostId();
        final int cost = 2;

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return "transaction failed due to user not found";
        }
        if (user.getCockroachesLeft() < cost) {
            return "transaction failed due to low balance";
        }

        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return "post is not available";
        }
        if(post.getUserId() == userId){
            return "cannot buy your own post";
        }

        if (LocalDate.now().isAfter(user.getLastRefillDate())) {
            user.setCockroachesLeft(100);
            user.setLastRefillDate(LocalDate.now());
        }

        boolean alreadyPurchased = purchaseRepository.existsByUserIdAndPostId(userId, postId);
        if (alreadyPurchased) {
            return "post is already bought by you";
        }

        user.setCockroachesLeft((user.getCockroachesLeft() - cost));
        user.setTotalCockroachesSpent((user.getTotalCockroachesSpent() + cost));
        post.setPostCockroaches(post.getPostCockroaches()+cost);
        postRepository.save(post);
        userRepository.save(user);

        Purchase purchase = new Purchase();
        purchase.setPostId(postId);
        purchase.setUserId(userId);
        purchaseRepository.save(purchase);

        return "transaction successful";
    }
}

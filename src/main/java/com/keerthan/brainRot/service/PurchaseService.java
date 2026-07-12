package com.keerthan.brainRot.service;

import com.keerthan.brainRot.dto.purchase.PurchaseRequestDTO;
import com.keerthan.brainRot.model.Purchase;
import com.keerthan.brainRot.model.User;
import com.keerthan.brainRot.repository.PostRepository;
import com.keerthan.brainRot.repository.PurchaseRepository;
import com.keerthan.brainRot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (user.getCockroaches_left() < cost) {
            return "transaction failed due to low balance";
        }

        boolean postExists = postRepository.existsById(postId);
        if (!postExists) {
            return "the post is not available";
        }

        boolean alreadyPurchased = purchaseRepository.existsByUserIdAndPostId(userId, postId);
        if (alreadyPurchased) {
            return "post is already bought by you";
        }

        user.setCockroaches_left(user.getCockroaches_left() - cost);
        user.setTotal_cockroaches_spent(user.getTotal_cockroaches_spent() + cost);
        userRepository.save(user);

        Purchase purchase = new Purchase();
        purchase.setPostId(postId);
        purchase.setUserId(userId);
        purchaseRepository.save(purchase);

        return "transaction successful";
    }
}

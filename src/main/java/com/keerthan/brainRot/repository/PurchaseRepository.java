package com.keerthan.brainRot.repository;

import com.keerthan.brainRot.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
    boolean existsByUserIdAndPostId(int userId, int postId);
}

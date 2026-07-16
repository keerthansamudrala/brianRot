package com.keerthan.brainRot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Table(name = "purchases")
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private int purchasesId;
    @Column(name = "user_id",nullable = false)
    private int userId;
    @Column(name = "post_id",nullable = false)
    private int postId;
    @CreationTimestamp
    @Column(name = "purchase_datetime")
    private LocalDateTime purchaseDateTime;
}

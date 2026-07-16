package com.keerthan.brainRot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_email", nullable = false , unique = true)
    private String userEmail;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name = "user_password", nullable = false)
    private String userPassword;
    @Column(name = "total_cockroaches_spent", nullable = true)
    private int totalCockroachesSpent;
    @Column(name = "cockroaches_left")
    private int cockroachesLeft = 100;
    @Column(name = "last_refill_date", nullable = false)
    private LocalDate lastRefillDate = LocalDate.now();

}

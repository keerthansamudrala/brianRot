package com.keerthan.brainRot.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(nullable = false , unique = true)
    private String user_email;
    @Column(nullable = false, unique = true)
    private String user_name;
    @Column(nullable = false)
    private String user_password;
    @Column(nullable = true)
    private int total_cockroaches_spent;

    private int cockroaches_left = 100;

}

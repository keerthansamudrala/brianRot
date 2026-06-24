package com.keerthan.brainRot.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Setter
@Getter
@ToString(exclude = "post_image")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;

    private int user_id;
    private String post_title;

    @Column(name = "post_image", nullable = true)
    private byte[] post_image;

    @CreationTimestamp
    private LocalDateTime post_datetime;

    @Column(nullable = false)
    private int post_cockroaches = 2;

}

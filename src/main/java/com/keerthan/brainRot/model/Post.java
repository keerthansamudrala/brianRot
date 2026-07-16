package com.keerthan.brainRot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString(exclude = "postImage")
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "post_title")
    private String postTitle;
    @Column(name = "post_image", nullable = true)
    private byte[] postImage;
    @CreationTimestamp
    @Column(name = "post_datetime")
    private LocalDateTime postDatetime;
    @Column(name = "post_cockroaches", nullable = false)
    private int postCockroaches = 2;

}

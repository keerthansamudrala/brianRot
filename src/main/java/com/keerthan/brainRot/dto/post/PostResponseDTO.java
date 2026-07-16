package com.keerthan.brainRot.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDTO {

    private int postId;
    private String postTitle;
    private byte[] postImage;
    private LocalDateTime postDatetime;
    private int postCockroaches;
    private int userId;

}

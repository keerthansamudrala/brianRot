package com.keerthan.brainRot.dto.post;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private String postTitle;
    private byte[] postImage;
    private int userId;
}

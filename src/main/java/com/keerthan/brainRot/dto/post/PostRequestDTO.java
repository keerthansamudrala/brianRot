package com.keerthan.brainRot.dto.post;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private String post_title;
    private byte[] post_image;
    private int user_id;
}

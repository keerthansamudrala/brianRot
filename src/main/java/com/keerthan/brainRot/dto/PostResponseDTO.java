package com.keerthan.brainRot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDTO {

    private int post_id;
    private String post_title;
    private byte[] post_image;
    private LocalDateTime post_datetime;
    private int post_cockroaches;

}

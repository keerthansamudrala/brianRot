package com.keerthan.brainRot.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private String user_name;
    private String user_email;
    private int total_cockroaches_spent;
    private int cockroaches_left;

}

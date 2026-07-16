package com.keerthan.brainRot.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private String userName;
    private String userEmail;
    private int totalCockroachesSpent;
    private int cockroachesLeft;

}

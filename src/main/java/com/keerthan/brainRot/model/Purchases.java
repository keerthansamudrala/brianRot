package com.keerthan.brainRot.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Purchases {

    @Id
    private int purchases_id;

    private int user_id;
    private int post_id;

}

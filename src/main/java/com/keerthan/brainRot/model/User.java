package com.keerthan.brainRot.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class User {

    @Id
    private int user_id;

    private String user_email;
    private String user_name;
    private String user_password;
    private int cockroaches_spent;
    private int cockroaches_left;

}

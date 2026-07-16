package com.keerthan.brainRot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "buy_later")
public class BuyLater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_later_id")
    private int id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "post_id")
    private int post_id;
    @Column(name = "status")
    private boolean status;

}

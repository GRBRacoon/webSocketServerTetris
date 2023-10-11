package com.example.webSocketServer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id@Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String password;
    private int win;
    private int lose;

    private float rate;

    public Member(){
        this.win=0;
        this.lose=0;
        this.rate=0;
    }
}

package com.example.board.domain.user.entity;

import com.example.board.dto.request.SignupRequest;

import java.time.LocalDateTime;

public class BoardUser {
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int loginTrial;
    private boolean activate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public int getLoginTrial() {return loginTrial;}

    public boolean isActivate() {return activate;}

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLoginTrial(int loginTrial) {
        this.loginTrial = loginTrial;
        if(this.loginTrial >= 5){
            this.activate = false;
        }
    }

    public void setActivate(boolean activate){this.activate = activate;}

    public void setNewUser(SignupRequest body) {
        this.setEmail(body.getEmail());
        this.setPassword(body.getPassword());
        this.setNickname(body.getNickname());
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.setLoginTrial(0);
        this.activate = true;
    }
}

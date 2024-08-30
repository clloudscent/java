package com.example.board.domain.user.entity;

import com.example.board.domain.user.entity.type.UserStatus;
import com.example.board.dto.request.SignupRequest;
import java.time.LocalDateTime;

public class BoardUser {
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private int failCount;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

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

    public void setNewUser(SignupRequest body) {
        this.setEmail(body.getEmail());
        this.setStatus(UserStatus.ACTIVE);
        this.setPassword(body.getPassword());
        this.setNickname(body.getNickname());
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void loginFail(){
        this.failCount++;
        if(this.failCount>=5){
            this.status = UserStatus.BLOCKED;
        }
    }

    public void loginSuccess() {
        this.failCount = 0;
    }
}

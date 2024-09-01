package org.example.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.board.dto.request.SignupRequest;

import java.time.LocalDateTime;

@Entity
public class BoardUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    // mysql `UNSIGNED`
    private Long id;
    private String nickname;
    private String email;
    private String password;
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    @Column(columnDefinition = "TINYINT UNSIGNED")
    private byte loginTrial;

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

    public byte getLoginTrial() {
        return loginTrial;
    }

    public void setLoginTrial(byte loginTrial) {
        this.loginTrial = loginTrial;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public void setNewUser(SignupRequest body){
        this.setEmail(body.getEmail());
        this.setPassword(body.getPassword());
        this.setNickname(body.getNickname());
        this.setActivate(true);
    }
}

package com.example.board.dto.request;

public class SignupRequest extends LoginRequest {
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

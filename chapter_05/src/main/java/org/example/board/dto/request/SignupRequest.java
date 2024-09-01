package org.example.board.dto.request;

public class SignupRequest extends LoginRequest {
    //회원가입
    //이메일
    private String nickname;

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
}

package org.example.board.dto.response;

public class CommonResponse {
    private String message;

    private CommonResponse() {
    }

    public String getMessage() {
        return message;
    }

    public static CommonResponse of(String message){
        CommonResponse response = new CommonResponse();
        response.message=message;
        return response;
    }
}

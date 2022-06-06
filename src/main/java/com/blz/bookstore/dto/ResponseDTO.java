package com.blz.bookstore.dto;



import com.blz.bookstore.model.UserRegistrationModel;
import lombok.Data;

import java.util.Optional;

public @Data class ResponseDTO {

    private String message;
    private Object data;
    private String token;
    private  Optional<UserRegistrationModel> userDetails;


    public ResponseDTO(String message) {
        this.message = message;
    }

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(String message, String token, Optional<UserRegistrationModel> userDetails) {
        this.message=message;
        this.token=token;
        this.userDetails=userDetails;
    }
}
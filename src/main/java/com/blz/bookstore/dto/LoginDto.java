package com.blz.bookstore.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class LoginDto {

    public String emailId;
    public String password;
    public String token;

}
package com.blz.bookstore.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class UserRegistrationDTO
{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @NotNull(message = "FullName is Required.")
    private String fullName;
    private String emailId;
    private String password;
   private String mobileNo;
    private String address;
    private String state;
    private String city;
    private String type;
    @NotNull(message = "KYC is Required.")
    @Size(min = 12, message = "KYC No should be of 12 digits.")
    private String kyc;
}
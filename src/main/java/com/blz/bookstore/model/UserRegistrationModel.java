package com.blz.bookstore.model;


import com.blz.bookstore.dto.UserRegistrationDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="UserRegistration")
@Data
public class UserRegistrationModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String fullName;
    private LocalDate registeredDate;
    private LocalDate updatedDate;
    @Column(name = "email")
    private String emailId;
    private String password;
    private String mobileNo;
    private boolean verify;


    public UserRegistrationModel() {}

    public UserRegistrationModel(int Id, UserRegistrationDTO userRegistrationDTO)
    {
        //this.mobileNo=userRegistrationDTO.getMobileNo();
        this.fullName = userRegistrationDTO.getFullName();
        this.emailId = userRegistrationDTO.getEmailId();
        this.password = userRegistrationDTO.getPassword();
    }



}
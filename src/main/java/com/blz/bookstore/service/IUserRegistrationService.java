package com.blz.bookstore.service;


import com.blz.bookstore.dto.LoginDto;
import com.blz.bookstore.dto.ResetPassword;
import com.blz.bookstore.dto.ResponseDTO;
import com.blz.bookstore.dto.UserRegistrationDTO;

public interface IUserRegistrationService
{

    ResponseDTO createUser(UserRegistrationDTO userDTO);

    ResponseDTO updateUserById(String token, int userid, UserRegistrationDTO userDTO);

    ResponseDTO updateUserById(String token, UserRegistrationDTO userDTO);

    ResponseDTO deleteUserById(String token, int userid);

    ResponseDTO loginUser(LoginDto loginDto);

    ResponseDTO forgotPassword(String email);

    Boolean verify(String token);

    ResponseDTO resetPassword(ResetPassword password, String token);

    int getUserId(String token);

}

package com.blz.bookstore.controller;



import com.blz.bookstore.dto.*;
import com.blz.bookstore.model.AddressModel;
import com.blz.bookstore.service.AddressService;
import com.blz.bookstore.service.IUserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserRegistrationController {

    @Autowired(required = true)
    private IUserRegistrationService registrationService;

    @Autowired
    private AddressService addressService;

    /**
     *
     * @param userDTO
     * @return
     */

    @PostMapping("/createuser")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserRegistrationDTO userDTO) {
        ResponseDTO userData = registrationService.createUser(userDTO);
        ResponseDTO resDTO = new ResponseDTO("Create User Details Sucessfully :");
        log.info(String.valueOf(resDTO));
        return new ResponseEntity<ResponseDTO>(resDTO, HttpStatus.OK);
    }

    /**
     *
     * @param token
     * @param userDTO
     * @return
     */

    @PutMapping("/updateuser")
    public ResponseEntity<ResponseDTO> updateUser(@RequestHeader("token") String token, @RequestBody UserRegistrationDTO userDTO) {
        ResponseDTO respDTO = registrationService.updateUserById(token, userDTO);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    /**
     *
     * @param token
     * @param userid
     * @return
     */

    @DeleteMapping("/deleteuser/{userid}")
    public ResponseEntity<ResponseDTO> deleteUser( @RequestHeader("token") String token, @PathVariable int userid) {
        ResponseDTO respDTO = registrationService.deleteUserById(token, userid);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    /**
     *
     * @param loginDto
     * @return
     */

    @PostMapping("/loginuser")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDto loginDto) {
        ResponseDTO respDTO = registrationService.loginUser(loginDto);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    /**
     *
     * @param email
     * @return
     */

    @GetMapping("/forgotpassword/{email}")
    public ResponseEntity<ResponseDTO> forgotPassword(@PathVariable String email) {
        ResponseDTO respDTO = registrationService.forgotPassword(email);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    /**
     *
     * @param token
     * @return
     */


    @GetMapping("/verifyemail/{token}")
    public Boolean verifyEmail(@PathVariable String token) {
        return registrationService.verify(token);
    }

    /**
     *
     * @param token
     * @return
     */

    @GetMapping("/getuserid/{token}")
    public int getUserId(@PathVariable String token) {
        return registrationService.getUserId(token);
    }

    /**
     *
     * @param password
     * @param token
     * @return
     */
    @PostMapping("/resetpassword/{token}")
    ResponseEntity<ResponseDTO> resetPassword(@RequestBody ResetPassword password, @PathVariable String token) {
        ResponseDTO respDTO = registrationService.resetPassword(password, token);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    @PostMapping("/address")
    public ResponseEntity<AddressModel> AddAddress(@RequestHeader("token") String token, @RequestBody AddressDTO addressDTO) {
        AddressModel addressModel = addressService.addAddress(addressDTO, token);
        return new ResponseEntity<AddressModel>(addressModel, HttpStatus.OK);
    }
}

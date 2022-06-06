package com.blz.bookstore.service;



import com.blz.bookstore.dto.LoginDto;
import com.blz.bookstore.dto.ResetPassword;
import com.blz.bookstore.dto.ResponseDTO;
import com.blz.bookstore.dto.UserRegistrationDTO;
import com.blz.bookstore.exception.UserRegistrationException;
import com.blz.bookstore.model.UserRegistrationModel;
import com.blz.bookstore.repository.UserRegistrationRepository;
import com.blz.bookstore.util.util.JMSUtil;
import com.blz.bookstore.util.util.TokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserRegistrationService implements IUserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRepository;

    @Autowired
    private JMSUtil jmsUtil;

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     *
     * @param userDTO
     * @return
     */

    @Override
    public ResponseDTO createUser(UserRegistrationDTO userDTO) {
        Optional<UserRegistrationModel> isUserPresent = userRepository.findByEmailId(userDTO.getEmailId());
        if (!isUserPresent.isPresent()) {

            // Encoding User Entered Password and saving into database
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            UserRegistrationModel createUser = modelmapper.map(userDTO, UserRegistrationModel.class);
            createUser.setRegisteredDate(LocalDate.now());
            createUser.setUpdatedDate(LocalDate.now());
            userRepository.save(createUser);
            jmsUtil.sendEmail(createUser.getEmailId(), "OTP for Book Store App", "Dear "+createUser.getFullName()+", The OTP for Book Store App is" + String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000)));
            return new ResponseDTO("User Register Sucessfully");
        } else {
            throw new UserRegistrationException(400, "User is already Register, Please Try with another Email Id");
        }
    }

    /**
     *
     * @param token
     * @param userid
     * @param userDTO
     * @return
     */
    @Override
    public ResponseDTO updateUserById(String token, int userid, UserRegistrationDTO userDTO) {
        return null;
    }

    @Override
    public ResponseDTO updateUserById(String token, UserRegistrationDTO userDTO) {
        int userId = TokenUtil.decodeToken(token);
        Optional<UserRegistrationModel> isUserPresent = userRepository.findById(userId);
        boolean userActive = isUserPresent.get().isVerify();
        if (isUserPresent.isPresent() && !userActive) {
            isUserPresent.get().setFullName(userDTO.getFullName());

            isUserPresent.get().setEmailId(userDTO.getEmailId());
            isUserPresent.get().setUpdatedDate(LocalDate.now());
            isUserPresent.get().setMobileNo(userDTO.getMobileNo());
            userRepository.save(isUserPresent.get());
            return new ResponseDTO("User Updated Successfully");
        } else {
            throw new UserRegistrationException(400, "User is already Register, Please Try with another Email Id");
        }
    }

    /**
     *
     * @param token
     * @param userid
     * @return
     */
    @Override
    public ResponseDTO deleteUserById(String token, int userid) {
        int userId = TokenUtil.decodeToken(token);
        Optional<UserRegistrationModel> isUserPresent = userRepository.findById(userId);
        userRepository.delete(new UserRegistrationModel());
        //return new ResponseDTO("User Deleted Successfully");
        if (isUserPresent.isPresent()) {
            isUserPresent.get().setVerify(true);
            return new ResponseDTO("User Deleted Successfully");
        } else {
            throw new UserRegistrationException(400, "User is already Register, Please Try with another Email Id");
        }
    }

    /**
     *
     * @param loginDto
     * @return
     */
    @Override
    public ResponseDTO loginUser(LoginDto loginDto) {
        Optional<UserRegistrationModel> isUserPresent = userRepository.findByEmailId(loginDto.emailId);
        String pass = passwordEncoder.encode(loginDto.getPassword());
        boolean isMatches = passwordEncoder.matches(loginDto.getPassword(), isUserPresent.get().getPassword());
        boolean userActive = isUserPresent.get().isVerify();
        if (isUserPresent.isPresent() && !userActive) {
            if (isUserPresent.get().getEmailId().equals(loginDto.emailId) && isMatches) {
                String token = TokenUtil.createToken(isUserPresent.get().getId());
                return new ResponseDTO("Login is Sucessfully", token, isUserPresent);
            } else {
                throw new UserRegistrationException(400, "Please check Email Id or Password, Retry");
            }
        } else {
            throw new UserRegistrationException(400, "User is already Register, Please Try with another Email Id");
        }
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public ResponseDTO forgotPassword(String email) {
        Optional<UserRegistrationModel> isUserPresent = userRepository.findByEmailId(email);
        if (isUserPresent.isPresent()) {
            String body = "http://localhost:4200/resetpassword/" + TokenUtil.createToken(isUserPresent.get().getId());
            jmsUtil.sendEmail(isUserPresent.get().getEmailId(), "Reset Password", body);
            return new ResponseDTO("Reset password link sent to your email " + email);
        } else {
            return new ResponseDTO("Your Email " + email + " is not registered with us ");
        }

    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public Boolean verify(String token) {
        int userId = TokenUtil.decodeToken(token);
        Optional<UserRegistrationModel> isUserPresent = userRepository.findById(userId);
        if (isUserPresent != null) {
            return true;

        } else {
            return false;
        }
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public int getUserId(String token) {
        int userId = TokenUtil.decodeToken(token);
        Optional<UserRegistrationModel> isUserPresent = userRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            return userId;
        } else {
            throw new UserRegistrationException(400, "User is already Register, Please Try with another Email Id");
        }
    }

    /**
     *
     * @param password
     * @param token
     * @return
     */
    @Override
    public ResponseDTO resetPassword(ResetPassword password, String token) {
        int userId = TokenUtil.decodeToken(token);
        Optional<UserRegistrationModel> isUserPresent = userRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            isUserPresent.get().setPassword(passwordEncoder.encode(password.getPassword()));
            isUserPresent.get().setUpdatedDate(LocalDate.now());

            userRepository.save(isUserPresent.get());
            return new ResponseDTO("Password updated successfully");
        } else {
            throw new UserRegistrationException(400, "Password reset failed");
        }
    }
}

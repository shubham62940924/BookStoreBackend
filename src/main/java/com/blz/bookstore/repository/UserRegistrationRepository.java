package com.blz.bookstore.repository;


import com.blz.bookstore.model.UserRegistrationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationModel, Integer> {

    Optional<UserRegistrationModel> findByEmailId(String emailId);

}
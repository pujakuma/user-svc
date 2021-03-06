package com.carwash.usersvc.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carwash.usersvc.model.User;
import com.carwash.usersvc.payload.request.ForgetPasswordRequest;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

User findByEmailIgnoreCase(String email);

ForgetPasswordRequest findByEmailIgnoreCase(ForgetPasswordRequest email);

 
  

}

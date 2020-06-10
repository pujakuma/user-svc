package com.carwash.usersvc.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carwash.usersvc.model.UserProfile;

public interface UserProfileRepository extends MongoRepository<UserProfile, String>{

	boolean existsByUserId(String userId);

	Optional<UserProfile> findByUserId(String id);

}

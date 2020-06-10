package com.carwash.usersvc.repository;

import org.springframework.data.repository.CrudRepository;

import com.carwash.usersvc.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);

}

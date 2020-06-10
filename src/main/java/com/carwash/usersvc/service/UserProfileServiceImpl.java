package com.carwash.usersvc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.usersvc.model.UserProfile;
import com.carwash.usersvc.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired
	private  UserProfileRepository userRepo;
	
	


	@Override
	public UserProfile createUserProfile(UserProfile request) {
		// verifyUserAccess(userId);
		UserProfile user=new UserProfile();
		String status="ACTIVE";
		request.setStatus(status);
		
		return userRepo.save(request);
		
	}

	@Override
	public UserProfile getUserInfoById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile deleteProfile(String id) {
		
		Optional<UserProfile> response = userRepo.findByUserId(id);
		//String status="DEACTIVATED";
		
		UserProfile userprofile=new UserProfile();
		String profileid=response.get().getId();

	    /*if (response.isPresent()) {
	    	userprofile=response.get();
	    	userprofile.setStatus(status);
	    }*/
	    	
		return null;
	    
		
	}
	

}

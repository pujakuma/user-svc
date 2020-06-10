package com.carwash.usersvc.service;

import com.carwash.usersvc.model.UserProfile;


public interface UserProfileService {
	
	public UserProfile createUserProfile(UserProfile request);

	public UserProfile getUserInfoById(String id);

	public UserProfile deleteProfile(String id);

	//public UserProfile getUserProfileById(String id);

	
}

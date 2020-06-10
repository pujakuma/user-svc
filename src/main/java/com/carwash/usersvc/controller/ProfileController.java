package com.carwash.usersvc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.usersvc.model.UserProfile;
import com.carwash.usersvc.payload.request.ProfilePutRequest;
import com.carwash.usersvc.payload.response.MessageResponse;
import com.carwash.usersvc.repository.UserProfileRepository;
import com.carwash.usersvc.repository.UserRepository;
import com.carwash.usersvc.service.UserProfileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProfileController {
	@Autowired
	UserProfileService profileService;
	@Autowired
	private  UserProfileRepository userRepo;
	@Autowired
	UserRepository userRepository;
	

	
	@PostMapping("/createProfile")
	  public ResponseEntity<MessageResponse> createProfile(@RequestBody UserProfile request) {
	    try {
	    	
	    	if (userRepo.existsByUserId(request.getUserId())) {
	    		return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: User is already created !"));
						
	    	}
	    	UserProfile response =profileService.createUserProfile(request);
	    	String id =response.getId();
	    	String message=id + "successfully created";
	      return new ResponseEntity<>( new MessageResponse(message),HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }

	  @GetMapping("/getprofile/{userid}")
	  public ResponseEntity<UserProfile> getUserProfileById(@PathVariable("userid") String id) {
		 
		  Optional<UserProfile> response = userRepo.findByUserId(id);
		  UserProfile userprofile= response.get();
		  
		  
		  String status=userprofile.getStatus();
	    if (response.isPresent() && ((status.equalsIgnoreCase("ACTIVE")))) {
	      return new ResponseEntity<>(response.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @DeleteMapping("/deleteprofile/{userid}")
	  public ResponseEntity<MessageResponse> deleteProfile(@PathVariable("userid") String id) {
	    try {
	    	//UserProfile response =profileService.deleteProfile(id);
	    	Optional<UserProfile> response = userRepo.findByUserId(id);
	    	String profileid=response.get().getId();
	    	userRepo.deleteById(profileid);
	    	userRepository.deleteById(id);
	    	
	    	//String message= id + "successfully deactivated";
	    	
	    	String message=profileid + "successfully deactivated";
	      return new ResponseEntity<>(new MessageResponse(message),HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	  @PutMapping("/updateprofile/{userid}")
	  public ResponseEntity<UserProfile> updateProfile(@PathVariable("userid") String id, @RequestBody ProfilePutRequest request) {
	    Optional<UserProfile> response = userRepo.findByUserId(id);

	    if (response.isPresent()) {
	    	UserProfile userprofile = response.get();
	    	userprofile.setAddress(request.getAddress());
	    	userprofile.setEmailId(request.getEmailId());
	    	userprofile.setMobileno(request.getMobileno());
	    	userprofile.setProfilPhoto(request.getProfilPhoto());
	      return new ResponseEntity<>(userRepo.save(userprofile), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}

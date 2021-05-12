package com.poc7.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.poc7.exception.ResourceNotFoundException;
import com.poc7.model.ResultModel;
import com.poc7.model.User;
import com.poc7.repository.UserRepository;
import com.poc7.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	@PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userid)
	        throws ResourceNotFoundException {
	        User user = userRepository.findById(userid)
	          .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userid));
	        return ResponseEntity.ok().body(user);
	    }
	
	@GetMapping("/users/fname")
	public List<User> getUserByName(String fname){
		return userRepository.findUserByFname(fname);
		}

	@PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
         @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

	
		user.setFname(userDetails.getFname());
		user.setLname(userDetails.getLname());
		user.setContact(userDetails.getContact());
		user.setEmail(userDetails.getEmail());
		user.setCity(userDetails.getCity());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
	
	 @DeleteMapping("/users/{id}")
	    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
	         throws ResourceNotFoundException {
	        User user = userRepository.findById(userId)
	       .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

	        userRepository.delete(user);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	 
	 
	 @GetMapping("/getAllUserTest")
	 public ResultModel getAllUsersTest() {
	 	return userService.getAllUser();
	 }
	 
	 @PostMapping("/createUser")
	 public ResultModel creatUserTest(@Valid @RequestBody User user) {
	 	return userService.createUser(user);
	 }
	 @GetMapping("/getUserById/{id}")
	 public ResultModel getUserByIdTest(@PathVariable (value="id") Long id) {
	 	return userService.getUserById(id);
	 }
	 
	 @PutMapping("/updateUser/{id}")
	 public ResultModel updateUserByIdTest(@Valid @RequestBody User userData , @PathVariable (value="id") Long id) {
	 	return userService.updateUserById(userData, id);
	 }
	 @DeleteMapping("/deletUser/{id}")
	 public ResultModel deleteUserByIdTest(@PathVariable (value="id") Long id) {
	 	return userService.deleteUserById(id);
	 }
}

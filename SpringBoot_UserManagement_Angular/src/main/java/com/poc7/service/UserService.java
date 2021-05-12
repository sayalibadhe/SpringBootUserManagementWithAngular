package com.poc7.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc7.model.ResultModel;
import com.poc7.model.User;
import com.poc7.repository.UserRepository;

@Service
public class UserService {
@Autowired 
private UserRepository userRepository;

public ResultModel getAllUser() {
	List<User> user=userRepository.findAll();
	if(user.size()==0) {
		return new ResultModel("No User were Found",Boolean.FALSE,"Failed");
	}
	else {
		return new ResultModel("Number Users were Found are "+user.size(),Boolean.TRUE,"Success");
	}
}
public ResultModel createUser(User user){
	userRepository.save(user);
	return new ResultModel("User with Name : "+user.getFname()+" and ID: "+user.getId()+" saved ",Boolean.TRUE,"Success");
}

public ResultModel getUserById(Long id){
	User user= userRepository.findUserById(id);
	if(user!=null) 
      	//return new ResultModel("No User with Id: "+id+" was found",Boolean.FALSE,"Failed");
		return new ResultModel(user.getFname()+ " with ID: "+id+" was found",Boolean.TRUE,"Success");
	else {
		//return new ResultModel(user.getFname()+ " with ID: "+id+" was found",Boolean.TRUE,"Success");
		return new ResultModel("No User with Id: "+id+" was found",Boolean.FALSE,"Failed");
	}
}
public ResultModel updateUserById(User user , Long id) {
	User userData=userRepository.findUserById(id);
	if(userData!=null) {
		userData.setFname(user.getFname());
		userData.setLname(user.getLname());
		userData.setContact(user.getContact());
		userData.setEmail(user.getEmail());
		userData.setCity(user.getCity());
		
		userRepository.save(userData);
		return new ResultModel("User with Name : "+userData.getFname()+" and ID: "+userData.getId()+" updated ",Boolean.TRUE,"Success");
	}
	else {
		return new ResultModel("No User with Employee-ID: "+id+" are found ",Boolean.FALSE,"Failed");
	}
}
public ResultModel deleteUserById(Long id){
	User user=userRepository.findUserById(id);
	if(user!=null) {
		userRepository.deleteById(id);
		return new ResultModel("User with  ID: "+id+" deleted ",Boolean.TRUE,"Success");
	}
	else {
		return new ResultModel("No User with ID: "+id+" are found ",Boolean.FALSE,"Failed");		
	}
}
}
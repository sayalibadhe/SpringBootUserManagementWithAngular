package com.poc7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc7.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	

	User findUserById(Long id);
	List<User> findUserByFname(String fname);


}

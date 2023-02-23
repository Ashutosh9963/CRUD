package com.learnsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsecurity.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByUsername(String username);

}

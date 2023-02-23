package com.learnsecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learnsecurity.model.User;
import com.learnsecurity.repository.UserRepository;
import com.learnsecurity.services.CustomUserDetailService;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private static final Logger logger=LoggerFactory.getLogger(CustomUserDetailService.class);
	public static void main(String[] args) {
		logger.info("Hello");
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail("john@gmail.com");
		user.setUsername("john");
		user.setPassword(this.bCryptPasswordEncoder.encode("123"));
		user.setRole("ROLE_USER");
		this.userRepository.save(user);
		
		User user1 = new User();
		user1.setEmail("ashu@gmail.com");
		user1.setUsername("ashu");
		user1.setPassword(this.bCryptPasswordEncoder.encode("1234"));
		user1.setRole("ROLE_ADMIN");
		this.userRepository.save(user1);
	}
}

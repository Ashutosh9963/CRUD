package com.learnsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learnsecurity.model.CustomUserDetail;
import com.learnsecurity.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.learnsecurity.model.User user = this.userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("NO USER");
		}
		return new CustomUserDetail(user);
	}

}

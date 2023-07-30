/*
package com.afroz.foodstall.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.afroz.foodstall.entities.User;
import com.afroz.foodstall.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByPhone(username);
		List<String>roles=new ArrayList<>(Arrays.asList("ROLE_USER"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword()
				, roles
				.stream()
				.map((role)-> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList())
				);
	}

}
*/
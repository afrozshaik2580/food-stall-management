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

import com.afroz.foodstall.entities.Stall;
import com.afroz.foodstall.repository.StallRepository;

@Service
public class StallDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	private StallRepository stallRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Stall stall=stallRepository.findByOwnerEmail(username);
		List<String>roles=new ArrayList<>(Arrays.asList("ROLE_USER"));
		return new org.springframework.security.core.userdetails.User(stall.getOwnerEmail(), stall.getPassword()
				, roles
				.stream()
				.map((role)-> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList())
				);
	}

}
*/
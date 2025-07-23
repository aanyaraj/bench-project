package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;


@Service
public class UserLoginService  implements UserDetailsService{
		
		@Autowired	
		private UserRepository userRepository;

		@Override
		public User loadUserByUsername(String username) throws UsernameNotFoundException {
			
			User user= userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
			return user;
			
		}
		  public UserDetails loadUserById(String id) throws UsernameNotFoundException {
		        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
		    }

	}


package com.example.demo.Runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.Model.Cart;
import com.example.demo.Model.User;
import com.example.demo.Repository.CartRepository;
import com.example.demo.Repository.UserRepository;


@Component
public class UserCommandlineRunner implements CommandLineRunner{
	  @Autowired
	  private  UserRepository userRepository;
	  @Autowired
	  private PasswordEncoder passwordEncoder;
	  @Autowired
	  private  CartRepository cartRepository;
	@Override
	public void run(String... args) throws Exception {
	      
	        User newUser = new User();
	        newUser.setName("Admin");
	        newUser.setEmail("Admin@gmail.com");
	        newUser.setPassword(passwordEncoder.encode("password"));
	        newUser.setRoles("ADMIN");
	        newUser.setMobileNo("9876543210");
	        Cart newCart = new Cart();
	        newUser.setCart(newCart);
	        cartRepository.save(newCart);
	        userRepository.save(newUser);
	 
	        System.out.println("ADMIN created successfully!");
	        
	    }
		
	}


package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Cart;
import com.example.demo.Model.Fruit;
import com.example.demo.Model.User;
import com.example.demo.Repository.CartRepository;
import com.example.demo.Repository.FruitRepository;
import com.example.demo.Repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FruitRepository fruitRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	

	public User addUser(User user) {
		
		 if (userRepository.existsByEmail(user.getEmail())) {
	            throw new RuntimeException("Email  already exist");
	        }

	        
	        if (userRepository.existsByMobileNo(user.getMobileNo())) {
	            throw new RuntimeException("Mobile number already exist");
	        }
	    user.setRoles("customer");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Cart cart = new Cart();
		user.setCart(cartRepository.save(cart));
		userRepository.save(user);
		
		
		
		return user;
		
	}
	
	public User getUser(String userId) throws Exception {
		
		return userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
		
	}
	public boolean addFruitToUser(String userId, Fruit fruit) {
		// TODO Auto-generated method stub
		return false;
	}

}

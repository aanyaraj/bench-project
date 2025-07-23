package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Constant.UserConstant;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = UserConstant.USER_BASE_URL)


public class UserController {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/getuser")
	public List<User> users(){
		List<User> userList = repository.findAll();
		return userList;
	}
	
	@PostMapping(path = {"/add"})
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public User addUser(@Valid @RequestBody User user) {
		
		return userService.addUser(user);
		
	}
	
	
	
	@GetMapping(path = {"getuser/{userId}"})
	public User getUser(@PathVariable(name="userId") String userId) throws Exception {
		
		return userService.getUser(userId);
		
	}

}

package com.example.demo.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	public Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);

    boolean existsByMobileNo(String mobileNo);

}

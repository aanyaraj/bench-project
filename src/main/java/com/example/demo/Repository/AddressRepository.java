package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	  @Query("SELECT a FROM Address a WHERE a.user.id = :userId")
	    List<Address> findByUserId(@Param("userId") Long userId);

}


package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Fruit;



@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {

	boolean existsByName(String name);

	List<Fruit> findAll();

	
    
}

package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.FruitCategory;



@Repository
public interface FruitCategoryRepository extends JpaRepository<FruitCategory, Long> {

	boolean existsByCategoryName(String categoryName);

	
	
}

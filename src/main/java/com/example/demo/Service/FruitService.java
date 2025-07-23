package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Model.Fruit;
import com.example.demo.Repository.FruitCategoryRepository;
import com.example.demo.Repository.FruitRepository;



@Service
public class FruitService {

 
    @Autowired
    private FruitRepository fruitRepository;
    
    @Autowired
    private FruitCategoryRepository fruitCategoryRepository;
 
    public List<Fruit> getAllFruits() {
        return fruitRepository.findAll();
    }
 
    public Fruit getFruitById(Long id) throws Exception {
        return fruitRepository.findById(id).orElseThrow(() -> new UserNotFoundException(" not found"+id));
    }
//    public FruitCategory getFruitCategoryByFruitId(Long fruitId) {
//        return fruitRepository.findFruitCategoryById(fruitId);
//    }

 
    public Fruit createFruit(Fruit fruit, Long categoryId) throws UserNotFoundException {
    	if (fruitRepository.existsByName(fruit.getName())) {
            throw new RuntimeException("fruit already exist");}
        
    	if(fruitCategoryRepository.findById(categoryId).isPresent()) 
    	{fruit.setFruitCategory(fruitCategoryRepository.findById(categoryId).get());
        return fruitRepository.save(fruit);}
    	else
    		throw new UserNotFoundException(" not found"+categoryId);
        
    }
 
    public Fruit updateFruit(Long id, Fruit fruit) {
        Fruit existingFruit = fruitRepository.findById(id).orElse(null);
        if (existingFruit != null) {
            existingFruit.setName(fruit.getName());
            existingFruit.setPrice(fruit.getPrice());
           
            return fruitRepository.save(existingFruit);
        }
        return null; 
    }

   
 
    public boolean deleteFruit(Long id) {
        if (fruitRepository.existsById(id)) {
            fruitRepository.deleteById(id);
            return true;
        }
        return false; 
    }
}
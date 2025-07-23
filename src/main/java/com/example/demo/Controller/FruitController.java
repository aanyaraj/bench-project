package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Constant.UserConstant;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Model.Fruit;
import com.example.demo.Service.FruitService;


@RestController
@RequestMapping(path = UserConstant.FRUIT_BASE_URL)


public class FruitController {
    @Autowired
    private FruitService fruitService;


@GetMapping

public ResponseEntity<List<Fruit>> getAllFruits() {
    List<Fruit> fruits = fruitService.getAllFruits();
    return !fruits.isEmpty()
            ? ResponseEntity.ok(fruits)
            : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
}


@GetMapping("/{id}")
public ResponseEntity<Fruit> getFruitById(@PathVariable Long id) throws Exception {
    
    	Fruit fruit = fruitService.getFruitById(id);
        
        return ResponseEntity.ok(fruit);}
    

//@GetMapping("/{fruitId}/category")
//public ResponseEntity<FruitCategory> getFruitCategoryByFruitId(@PathVariable Long fruitId) {
//    FruitCategory fruitCategory = fruitService.getFruitCategoryByFruitId(fruitId);
//    if (fruitCategory != null) {
//        return new ResponseEntity<>(fruitCategory, HttpStatus.OK);
//    } else {
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//}

@PostMapping("/create/{categoryId}")
public ResponseEntity<Fruit> createFruit(@RequestBody Fruit fruit, @PathVariable Long categoryId) throws UserNotFoundException {
    Fruit createdFruit = fruitService.createFruit(fruit, categoryId);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdFruit);
}


@PutMapping("/{id}")
public ResponseEntity<Fruit> updateFruit(@PathVariable Long id, @RequestBody Fruit fruit) {
    Fruit updatedFruit = fruitService.updateFruit(id, fruit);
    return updatedFruit != null ? ResponseEntity.ok(updatedFruit): ResponseEntity.notFound().build();
}


@DeleteMapping("/{id}") 
public ResponseEntity<String> deleteFruit(@PathVariable Long id) {
    boolean deleted = fruitService.deleteFruit(id);
    
    return deleted ? ResponseEntity.ok("Fruit deleted successfully") : ResponseEntity.notFound().build();
}
}

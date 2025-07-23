package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
import com.example.demo.Model.FruitCategory;
import com.example.demo.Service.FruitCategoryService;


@RestController
@RequestMapping(path = UserConstant.CATEGORY_BASE_URL)


public class FruitCategoryController {

    @Autowired
    private FruitCategoryService fruitCategoryService;


  
    @GetMapping
    public ResponseEntity<List<FruitCategory>> getAllCategories() {
        List<FruitCategory> categories = fruitCategoryService.getAllCategories();
        return !categories.isEmpty()
                ? ResponseEntity.ok(categories)
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<FruitCategory> getCategoryById(@PathVariable Long id) {
        FruitCategory fruitCategory = fruitCategoryService.getCategoryById(id);
        return fruitCategory != null
            ? ResponseEntity.ok(fruitCategory)
        
             : ResponseEntity.notFound().build();
    }

  
    @PostMapping
    public ResponseEntity<FruitCategory> createCategory(@RequestBody FruitCategory fruitCategory) {
        FruitCategory createdCategory = fruitCategoryService.createCategory(fruitCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FruitCategory> updateCategory(@PathVariable Long id, @RequestBody FruitCategory fruitCategory) {
        FruitCategory updatedCategory = fruitCategoryService.updateCategory(id, fruitCategory);
        return updatedCategory != null
                ? ResponseEntity.ok(updatedCategory)
                : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        boolean deleted = fruitCategoryService.deleteCategory(id);
        return deleted
                ? ResponseEntity.ok("Category deleted successfully")
                : ResponseEntity.notFound().build();
    }
}
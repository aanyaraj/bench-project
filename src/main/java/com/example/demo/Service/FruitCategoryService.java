package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.FruitCategory;
import com.example.demo.Repository.FruitCategoryRepository;


@Service
public class FruitCategoryService {

    @Autowired
    private FruitCategoryRepository fruitCategoryRepository;

    public List<FruitCategory> getAllCategories() {
        return fruitCategoryRepository.findAll();
    }

    public FruitCategory getCategoryById(Long id) {
        return fruitCategoryRepository.findById(id).orElse(null);
    }
    
    public FruitCategory createCategory(FruitCategory fruitCategory) {
    	if (fruitCategoryRepository.existsByCategoryName(fruitCategory.getCategoryName())) {
            throw new RuntimeException("category already exist");}
        return fruitCategoryRepository.save(fruitCategory);
    }

    public FruitCategory updateCategory(Long id, FruitCategory fruitCategory) {
        FruitCategory existingCategory = fruitCategoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setCategoryName(fruitCategory.getCategoryName());
            existingCategory.setDescription(fruitCategory.getDescription());
            // Update other category attributes if needed
            return fruitCategoryRepository.save(existingCategory);
        }
        return null; // Handle update failure if needed
    }

    public boolean deleteCategory(Long id) {
        if (fruitCategoryRepository.existsById(id)) {
            fruitCategoryRepository.deleteById(id);
            return true;
        }
        return false; // Handle delete failure if needed
    }
}
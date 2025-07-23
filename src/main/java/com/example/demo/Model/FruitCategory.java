package com.example.demo.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class FruitCategory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6242410383491071329L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private String description;
    

    @OneToMany(mappedBy = "fruitCategory")
//	@JoinColumn(name="id",referencedColumnName = "id")
	private Set<Fruit> fruits = new HashSet<>();
   
    @Override
	public String toString() {
		return "FruitCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", description="
				+ description +  "]";
	}

	public FruitCategory(Long categoryId, String categoryName, String description) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.description = description;
		
	}

	


	public FruitCategory() {
    }

    public FruitCategory(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    // Getters and Setters
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

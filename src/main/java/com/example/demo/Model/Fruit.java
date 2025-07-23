package com.example.demo.Model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Fruit implements Serializable {
    

	


	/**
	 * 
	 */
	private static final long serialVersionUID = 7904301199355263677L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fruitId;
    private String name;
    private double price;
    private String description;
    private int fruitStock;
    
    @ManyToOne
    @JoinColumn(name = "categoryId",referencedColumnName = "categoryId")
	@JsonIgnoreProperties(value = {"fruits","hibernateLazyInitializer"})
    private FruitCategory fruitCategory;

    // Constructors, getters, setters, and other methods

    // Constructors
    public Fruit() {
    }

    public Fruit(Long fruitId,String name, double price, String description, FruitCategory fruitCategory) {
    	this.fruitId = fruitId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.fruitCategory = fruitCategory;
    }

  

	public FruitCategory getFruitCategory() {
		return fruitCategory;
	}

	public void setFruitCategory(FruitCategory fruitCategory) {
		this.fruitCategory = fruitCategory;
	}

	// Getters and Setters
    public Long getFruitId() {
        return fruitId;
    }

    public void setFruitId(Long fruitId) {
        this.fruitId = fruitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public int getFruitStock() {
		return fruitStock;
	}

	public void setFruitStock(int fruitStock) {
		this.fruitStock = fruitStock;
	}


	@Override
	public String toString() {
		return "Fruit [fruitId=" + fruitId + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", fruitStock=" + fruitStock + ", fruitCategory=" + fruitCategory + "]";
	}
}
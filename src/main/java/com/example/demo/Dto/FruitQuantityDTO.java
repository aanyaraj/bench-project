package com.example.demo.Dto;

public class FruitQuantityDTO {
    private Long fruitId;
    private String name;
    private Double price;
    private String description;
    private Integer fruitStock;
    private String fruitCategory;
    private Integer quantity;
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getFruitCategory() {
		return fruitCategory;
	}
	public void setFruitCategory(String fruitCategory) {
		this.fruitCategory = fruitCategory;
	}
	public Integer getFruitStock() {
		return fruitStock;
	}
	public void setFruitStock(Integer fruitStock) {
		this.fruitStock = fruitStock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getFruitId() {
		return fruitId;
	}
	public void setFruitId(Long fruitId) {
		this.fruitId = fruitId;
	}

    // Getters and setters
}


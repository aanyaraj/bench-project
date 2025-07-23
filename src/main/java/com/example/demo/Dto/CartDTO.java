package com.example.demo.Dto;

import java.util.List;

public class CartDTO {
    private String cartId;
    private Double cartPrice;
    private List<FruitQuantityDTO> fruitandQuantity;
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String string) {
		this.cartId = string;
	}
	public Double getCartPrice() {
		return cartPrice;
	}
	public void setCartPrice(Double cartPrice) {
		this.cartPrice = cartPrice;
	}
	public List<FruitQuantityDTO> getFruitandQuantity() {
		return fruitandQuantity;
	}
	public void setFruitandQuantity(List<FruitQuantityDTO> fruitandQuantity) {
		this.fruitandQuantity = fruitandQuantity;
	}

    // Getters and setters
}

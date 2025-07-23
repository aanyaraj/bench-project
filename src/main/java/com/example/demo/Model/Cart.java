package com.example.demo.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -2580127060830270268L;
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private String cartId;
 
	@Column(name = "cart_price")
	private Double cartPrice;
 
	@ElementCollection
	@CollectionTable(name = "Fruit_and_quantity_Cart", joinColumns = @JoinColumn(name = "cart_id"))
	@MapKeyColumn(name = "FruitId")
	@Column(name = "Fruit_quantity")
	private Map<Fruit, Integer> FruitandQuantity = new HashMap<>();


    @PrePersist
    public void prePersist() {
        if (this.cartId == null) {
            this.cartId = UUID.randomUUID().toString();
        }
    }
//	public Long getCartId() {
//		return cartId;
//	}
//
//	public void setCartId(Long cartId) {
//		this.cartId = cartId;
//	}
//
//	public Double getCartPrice() {
//		return cartPrice;
//	}
//
//	public void setCartPrice(Double cartPrice) {
//		this.cartPrice = cartPrice;
//	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(Double cartPrice) {
		this.cartPrice = cartPrice;
	}

//	public Map<Fruit, Integer> getFruitandQuantity() {
//		return FruitandQuantity;
//	}
//
//	public void setFruitandQuantity(Map<Fruit, Integer> fruitandQuantity) {
//		FruitandQuantity = fruitandQuantity;
//	}

	public void setFruitandQuantity(Map<Fruit, Integer> fruitandQuantity) {
		// TODO Auto-generated method stub
		FruitandQuantity = fruitandQuantity;
	}

	public Map<Fruit, Integer> getFruitandQuantity() {
		// TODO Auto-generated method stub
		return FruitandQuantity;
	}


 
	
 

	
 
}
	

	
	

	

    


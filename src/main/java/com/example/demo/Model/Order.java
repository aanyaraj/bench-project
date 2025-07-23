package com.example.demo.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 102542129465306853L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private Double orderPrice;
	

	private LocalDateTime orderDateTime;
	private LocalDateTime orderDeliveryDateTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private OrderStatus orderStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Transaction_id", referencedColumnName = "transactionId")
	@JsonIgnoreProperties(value = {"order","hibernateLazyInitializer"})
	private Transaction transaction;
	
	@JsonIncludeProperties(value = {"userId","name","email"})
	@ManyToOne
	@JoinColumn(referencedColumnName="userId", name="user_id")
	private User user;
	

	@ElementCollection
	@CollectionTable(name = "Fruit_and_quantity_Order", joinColumns = @JoinColumn(name = "order_id"))
	@MapKeyColumn(name = "FruitId")
	@Column(name = "Fruit_quantity")
	private Map<Fruit, Integer> FruitandQuantity = new HashMap<>();
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public LocalDateTime getOrderDeliveryDateTime() {
		return orderDeliveryDateTime;
	}

	public void setOrderDeliveryDateTime(LocalDateTime orderDeliveryDateTime) {
		this.orderDeliveryDateTime = orderDeliveryDateTime;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Fruit, Integer> getFruitandQuantity() {
		return FruitandQuantity;
	}

	public void setFruitandQuantity(Map<Fruit, Integer> fruitandQuantity) {
		FruitandQuantity = fruitandQuantity;
	}
	
	   private String address;  // Add this field

	    // Getters and setters
	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderPrice=" + orderPrice + ", orderDateTime=" + orderDateTime
				+ ", orderDeliveryDateTime=" + orderDeliveryDateTime + ", orderStatus=" + orderStatus + ", transaction="
				+ transaction + ", user=" + user + ", FruitandQuantity=" + FruitandQuantity + "]";
	}




}

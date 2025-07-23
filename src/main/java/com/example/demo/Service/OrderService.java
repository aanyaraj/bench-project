package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderStatus;
import com.example.demo.Model.Transaction;
import com.example.demo.Model.TransactionStatus;
import com.example.demo.Model.User;
import com.example.demo.Repository.FruitRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final FruitRepository fruitRepository;
	private final UserRepository userRepository;
	private final TransactionRepository transactionRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, FruitRepository fruitRepository,UserRepository userRepository,
			TransactionRepository transactionRepository) {
		this.orderRepository = orderRepository;
		this.fruitRepository = fruitRepository;
		this.userRepository =userRepository;
		this.transactionRepository=transactionRepository;
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order getOrder(Long orderId) throws Exception {
		return orderRepository.findById(orderId).orElseThrow(() -> new UserNotFoundException("Order not found"+orderId));
	}


	
	

	    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) throws Exception {
	        Order order = orderRepository.findById(orderId)
	                .orElseThrow(() -> new Exception("Order not found"));

	        // Check if the order is already canceled or delivered
	        if (order.getOrderStatus() == OrderStatus.CANCELLED || order.getOrderStatus() == OrderStatus.DELIVERED) {
	            throw new Exception("Cannot update status. Order is already cancelled or delivered");
	        }

	        // Update the order status
	        order.setOrderStatus(newStatus);

	        // If the new status is DELIVERED, update the delivery date to LocalDateTime.now()
	        if (newStatus == OrderStatus.DELIVERED) {
	            order.setOrderDeliveryDateTime(LocalDateTime.now());
	        }

	        return orderRepository.save(order);
	    }
	

	public Order cancelOrder(Long orderId) throws Exception {
	    Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new Exception("Order not found"));
	 
	    if (order.getOrderStatus() == OrderStatus.DELIVERED) {
	        throw new Exception("Order has already been delivered, cannot be canceled.");
	    }
	 
	    // Perform the refund operation if there's a transaction associated with the order
	    Transaction transaction = order.getTransaction();
	    if (transaction != null) {
	        transaction.setTransactionStatus(TransactionStatus.REFUNDED);
	        transactionRepository.save(transaction);
	    }
	 
	    // Update the order status to CANCELLED and reset delivery date time
	    order.setOrderStatus(OrderStatus.CANCELLED);
	    order.setOrderDeliveryDateTime(null);
	 
	    return orderRepository.save(order);
	}
	



public List<Order> getAllOrdersByUserId(String userId) {
    
    User user = userRepository.findById(userId)
    		.orElseThrow(() -> new EntityNotFoundException("User not found"));

    
    List<Order> orders = orderRepository.findByUser(user);
    return orders;
}
}

package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Constant.UserConstant;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderStatus;
import com.example.demo.Service.OrderService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path=UserConstant.ORDER_BASE_URL)


public class OrderController {

	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/all")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) throws Exception {
		
			Order order = orderService.getOrder(orderId);
			return ResponseEntity.ok(order);
 
		}
	
	  @ExceptionHandler(EntityNotFoundException.class)
	    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Entity not found: " + ex.getMessage());
	    }
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getAllOrdersByUserId(@PathVariable String userId) {
        List<Order> orders = orderService.getAllOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }



	@PutMapping("/{orderId}/update-status")
	public ResponseEntity<Object> updateOrderStatus(@PathVariable Long orderId,
	                                               @RequestParam OrderStatus newStatus) throws Exception {
	  
	        Order updatedOrder = orderService.updateOrderStatus(orderId, newStatus);
	        return ResponseEntity.ok(updatedOrder);
	    }
	 
	
	

		
	@PutMapping("/cancel/{orderId}")
	public ResponseEntity<Object> cancelOrder(@PathVariable Long orderId) throws Exception {
	
	        Order cancelledOrder = orderService.cancelOrder(orderId);
	        return ResponseEntity.ok(cancelledOrder);
	    }

	}

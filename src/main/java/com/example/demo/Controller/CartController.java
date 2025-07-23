package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Constant.UserConstant;
import com.example.demo.Dto.CartDTO;
import com.example.demo.Model.Cart;
import com.example.demo.Model.Fruit;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderStatus;
import com.example.demo.Model.Transaction;
import com.example.demo.Model.TransactionStatus;
import com.example.demo.Model.User;
import com.example.demo.Repository.CartRepository;
import com.example.demo.Repository.FruitRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.CartService;


@RestController
@RequestMapping(path=UserConstant.CART_BASE_URL)


public class CartController {

	private final CartService cartService;
	@Autowired
	private FruitRepository fruitRepository;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping
	public List<Cart> getAllCarts() {
		return cartService.getAllCarts();
	}


	
	@GetMapping("/{userId}")
	public ResponseEntity<CartDTO> getCartByUserId(@PathVariable String userId) throws Exception {
	    CartDTO cart = cartService.getCartByUserId(userId);
	    return ResponseEntity.ok(cart);
	}

	
	


	@DeleteMapping("/{cartId}")
	public ResponseEntity<Void> deleteCart(@PathVariable String cartId) {
		cartService.deleteCart(cartId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	 @PostMapping("/place-order/{userId}")
	    public ResponseEntity<Object> placeOrderIfCartMatchesTransaction(
	            @PathVariable String userId,
	            @RequestParam(name = "transactionAmount") double transactionAmount,
	            @RequestParam(name = "addressId", required = false) Long addressId,
	            @RequestParam(name = "newAddress", required = false) String newAddress) throws Exception {
	        
	        Order placedOrder = cartService.placeOrderIfCartMatchesTransaction(userId, transactionAmount, addressId, newAddress);
	        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
	    }
    



	
	@PutMapping(path = "/addToCart/{cartId}/{fruitId}/{quantity}")
	public Cart addProductToCart(@PathVariable(name = "fruitId") Long productId,
			@PathVariable(name = "cartId") String cartId, @PathVariable(name = "quantity") Integer quantity)
			throws Exception 
	{
		
		return cartService.addProduct(cartId, productId, quantity);
				//addProduct(cartId, productId, quantity);

	}



	@PutMapping("/fruit/{userId}/{fruitId}")
	public ResponseEntity<Object> updateFruitQuantityInCart(@PathVariable String userId, @PathVariable Long fruitId,
	        @RequestParam Integer newQuantity) throws Exception {

	   
	        Cart updatedCart = cartService.updateFruitQuantityInCart(userId, fruitId, newQuantity);
	        return new ResponseEntity<>(updatedCart, HttpStatus.CREATED);
	   
	       
	    
	}
		
			
		

	@DeleteMapping("/fruit/{userId}/{fruitId}")
	public ResponseEntity<Object> removeFruitFromCart(@PathVariable String userId, @PathVariable Long fruitId) throws Exception {
		
			Cart updatedCart = cartService.removeFruitFromCart(userId, fruitId);
			return new ResponseEntity<Object>(updatedCart, HttpStatus.CREATED);
	
		
		
	}}
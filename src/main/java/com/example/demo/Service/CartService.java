package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Constant.UserConstant;
import com.example.demo.Dto.CartDTO;
import com.example.demo.Dto.FruitQuantityDTO;
import com.example.demo.Model.Address;
import com.example.demo.Model.Cart;
import com.example.demo.Model.Fruit;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderStatus;
import com.example.demo.Model.Transaction;
import com.example.demo.Model.TransactionStatus;
import com.example.demo.Model.User;
import com.example.demo.Repository.AddressRepository;
import com.example.demo.Repository.CartRepository;
import com.example.demo.Repository.FruitRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class CartService {

	private final CartRepository cartRepository;
	private final OrderRepository orderRepository;
	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	private final FruitRepository fruitRepository;
	private final AddressRepository addressRepository;


	@Autowired
	public CartService(CartRepository cartRepository, OrderRepository orderRepository,
			TransactionRepository transactionRepository, UserRepository userRepository, FruitRepository fruitRepository, AddressRepository addressRepository ) {
		this.cartRepository = cartRepository;
		this.transactionRepository = transactionRepository;
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.fruitRepository = fruitRepository;
		this.addressRepository = addressRepository;

	}

	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}

	public Optional<Cart> getCartById(String cartId) {
		return cartRepository.findById(cartId);
	}


//	public Cart createCart(Cart cart) {
//		System.out.println("created, add fruit");
//		return cartRepository.save(cart);
//	}

	public void deleteCart(String cartId) {
		cartRepository.deleteById(cartId);
	}

	public CartDTO getCartByUserId(String userId) throws Exception {
	    User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

	    Cart cart = user.getCart();

	    if (cart == null) {
	        throw new Exception("Cart not found for the user");
	    }

	    CartDTO cartDTO = new CartDTO();
	    cartDTO.setCartId(cart.getCartId());
	    cartDTO.setCartPrice(cart.getCartPrice());

	    List<FruitQuantityDTO> fruitQuantityDTOList = cart.getFruitandQuantity().entrySet().stream().map(entry -> {
	        Fruit fruit = entry.getKey();
	        Integer quantity = entry.getValue();
	        FruitQuantityDTO dto = new FruitQuantityDTO();
	        dto.setFruitId(fruit.getFruitId());
	        dto.setName(fruit.getName());
	        dto.setPrice(fruit.getPrice());
	        dto.setDescription(fruit.getDescription());
	        dto.setFruitStock(fruit.getFruitStock());
	        dto.setFruitCategory(fruit.getFruitCategory().getCategoryName());
	        dto.setQuantity(quantity);
	        return dto;
	    }).collect(Collectors.toList());

	    cartDTO.setFruitandQuantity(fruitQuantityDTOList);

	    return cartDTO;
	}

	public Order placeOrderIfCartMatchesTransaction(String userId, double transactionAmount, Long addressId, String newAddress) {
	    User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

	    Cart cart = user.getCart();
	    if (cart == null) {
	        throw new IllegalArgumentException(UserConstant.CART_EMPTY);
	    }

	    double cartPrice = cart.getCartPrice();
	    if (cartPrice != transactionAmount) {
	        throw new IllegalArgumentException(UserConstant.NOT_PLACED);
	    }

	    Address address = null;
	    if (addressId != null) {
	        address = addressRepository.findById(addressId).orElseThrow(() -> new IllegalArgumentException("Address not found"));
	    } else if (newAddress != null) {
	        address = new Address();
	        address.setAddressLine(newAddress);
	        address.setUser(user);
	        addressRepository.save(address);
	    } else {
	        throw new IllegalArgumentException(UserConstant.ADD_WRONG);
	    }

	    Map<Fruit, Integer> fruitsAndQuantities = cart.getFruitandQuantity();
	    for (Map.Entry<Fruit, Integer> entry : fruitsAndQuantities.entrySet()) {
	        Fruit fruit = entry.getKey();
	        int requestedQuantity = entry.getValue();
	        int availableStock = fruit.getFruitStock();
	        if (requestedQuantity > availableStock) {
	            throw new IllegalArgumentException("Insufficient stock for fruit: " + fruit.getFruitId());
	        }

	        fruitRepository.save(fruit);
	    }

	    Order order = new Order();
	    order.setUser(user);
	    order.setOrderDateTime(LocalDateTime.now());
	    order.setOrderDeliveryDateTime(LocalDateTime.now().plusDays(5));
	    order.setAddress(address.getAddressLine());
	    Transaction transaction = new Transaction();
	    transaction.setTransactionAmount(transactionAmount);
	    transaction.setTransactionStatus(TransactionStatus.COMPLETED);
	    transactionRepository.save(transaction);
	    order.setTransaction(transaction);
	    order.setOrderPrice(calculateCartAmount(cart));

	    Map<Fruit, Integer> fruits = new HashMap<>(cart.getFruitandQuantity());
	    fruits.forEach((key, value) -> {
	        key.setFruitStock(key.getFruitStock() - value);
	        fruitRepository.save(key);
	    });

	    order.setOrderStatus(OrderStatus.COMPLETED);
	    cart.getFruitandQuantity().clear();
	    cartRepository.save(cart);
	    order.setFruitandQuantity(fruits);
	    order.setUser(user);

	    orderRepository.save(order);

	    return order;
	}



	private Transaction createTransactionWithAmount(double transactionAmount) {
		Transaction newTransaction = new Transaction();
		newTransaction.setTransactionAmount(transactionAmount);
		
		return transactionRepository.save(newTransaction);
	}

	private Order createOrderFromCart(Cart cart) {
		Order newOrder = new Order();
		newOrder.setOrderPrice(cart.getCartPrice());
		return newOrder;
	}

	public Cart addProduct(String cartId, Long productId, Integer quantity) throws Exception {

		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new Exception(" not found"));

		cart.getFruitandQuantity().put(
				fruitRepository.findById(productId).orElseThrow(() -> new Exception("Product not found")), quantity);
		Fruit product = fruitRepository.findById(productId).orElseThrow(()->new Exception("not found"));
		int availableStock = product.getFruitStock();
		 
		if (quantity > availableStock) {
			throw new Exception(UserConstant.NOT_ADD_CART);
		}

		cart.setCartPrice(calculateCartAmount(cart));

		return cartRepository.save(cart);

	}

	private Double calculateCartAmount(Cart cart) {

		return cart.getFruitandQuantity().entrySet().stream().map(entry -> entry.getKey().getPrice() * entry.getValue())
				.mapToDouble(Double::valueOf).sum();

	}

	public Cart updateFruitQuantityInCart(String userId, Long fruitId, Integer newQuantity) throws Exception {
	    User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

	    Cart cart = user.getCart();
	    if (cart == null) {
	        throw new Exception("Cart not found for the user");
	    }

	    Fruit fruit = fruitRepository.findById(fruitId).orElseThrow(() -> new Exception("Fruit not found"));

	    Map<Fruit, Integer> fruitAndQuantity = cart.getFruitandQuantity();

	    if (!fruitAndQuantity.containsKey(fruit)) {
	        throw new Exception("Fruit not found in the cart");
	    }

	    fruitAndQuantity.put(fruit, newQuantity);
	    cart.setFruitandQuantity(fruitAndQuantity);
	    cart.setCartPrice(calculateCartAmount(cart));
	    
	    // Save the updated cart and return it
	    return cartRepository.save(cart);
	}

	public Cart removeFruitFromCart(String userId, Long fruitId) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

		Cart cart = user.getCart();

		if (cart == null) {
			throw new Exception("Cart not found for the user");
		}

		Fruit fruit = fruitRepository.findById(fruitId).orElseThrow(() -> new Exception("fruit not found"));

		Map<Fruit, Integer> fruitAndQuantity = cart.getFruitandQuantity();

		if (fruitAndQuantity.containsKey(fruit)) {
			fruitAndQuantity.remove(fruit);
			cart.setFruitandQuantity(fruitAndQuantity);
			cart.setCartPrice(calculateCartAmount(cart));
			return cartRepository.save(cart);
		} else {
			throw new Exception("fruit not found in the cart");
		}
	}
	

   
}
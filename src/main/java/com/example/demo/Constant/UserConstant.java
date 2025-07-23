package com.example.demo.Constant;

public class UserConstant {

	public static final String TABLE_NAME= "Users";
	
	public static final String ERROR_EMPTY= "Required field";
	
	public static final String USER_BASE_URL= "/user";
	public static final String LOGIN_BASE_URL= "/auth";
	public static final String FRUIT_BASE_URL= "/fruits";
	public static final String CATEGORY_BASE_URL= "/categories";
	public static final String CART_BASE_URL= "/carts";
	public static final String ORDER_BASE_URL= "/orders";
	public static final String ADDRESS_BASE_URL= "/addresses";
	
	
	public static final String NOT_PLACED= "Cart price doesn't match the transaction amount";
	public static final String CART_EMPTY= "User's cart is empty";
	public static final String ADD_WRONG= "Either addressId or newAddress must be provided";
	public static final String NOT_ADD_CART= "Quantity exceeds available stock, cannot add to the cart.";
}

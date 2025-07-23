//package com.example.demo.Service;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.Model.Order;
//import com.razorpay.RazorpayClient;
//
//@Service
//public class PaymentService {
//
//    @Value("${razorpay.key_id}")
//    private String razorpayKeyId;
//
//    @Value("${razorpay.key_secret}")
//    private String razorpayKeySecret;
//
//    public String createOrder(double amount) throws Exception {
//        RazorpayClient client = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
//
//        JSONObject orderRequest = new JSONObject();
//        orderRequest.put("amount", amount * 100); // amount in the smallest currency unit (e.g., paise)
//        orderRequest.put("currency", "INR");
//        orderRequest.put("receipt", "receipt_order_123");
//
//        com.razorpay.Order order = client.orders.create(orderRequest);
//        return order.toString();
//    }
//}
//

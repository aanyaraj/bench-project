//package com.example.demo.Controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.demo.Service.PaymentService;
//import com.razorpay.Utils;
//
//@RestController
//@RequestMapping("/api/payment")
//public class PaymentController {
//
//    @Autowired
//    private PaymentService paymentService;
//    
//    @Value("${razorpay.key_secret}")
//    private String razorpayKeySecret;
//
//    @PostMapping("/create-order")
//    public ResponseEntity<String> createOrder(@RequestParam double amount) {
//        try {
//            String order = paymentService.createOrder(amount);
//            return ResponseEntity.ok(order);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error creating order: " + e.getMessage());
//        }
//    }
//   
//}
//

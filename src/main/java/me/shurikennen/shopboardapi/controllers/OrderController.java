package me.shurikennen.shopboardapi.controllers;


import lombok.RequiredArgsConstructor;
import me.shurikennen.shopboardapi.entities.Order;
import me.shurikennen.shopboardapi.repositories.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/orders")
@CrossOrigin
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping("/all/{server}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable String server) {
        return ResponseEntity.ok(orderRepository.getByServer(server));
    }


}

package me.shurikennen.shopboardapi.services;

import lombok.RequiredArgsConstructor;
import me.shurikennen.shopboardapi.entities.Order;
import me.shurikennen.shopboardapi.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;


    public List<Order> getOrders() {
        return repository.findAll();
    }

}

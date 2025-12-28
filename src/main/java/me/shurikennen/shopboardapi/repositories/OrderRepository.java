package me.shurikennen.shopboardapi.repositories;

import me.shurikennen.shopboardapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getByServer(String server);

}

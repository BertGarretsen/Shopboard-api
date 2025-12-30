package me.shurikennen.shopboardapi.services;

import lombok.RequiredArgsConstructor;
import me.shurikennen.shopboardapi.entities.Order;
import me.shurikennen.shopboardapi.entities.Shop;
import me.shurikennen.shopboardapi.repositories.OrderRepository;
import me.shurikennen.shopboardapi.repositories.ShopRepository;
import me.shurikennen.shopboardapi.response.ShopResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;

    public List<ShopResponse> getAllOrders(String serverHash) {
        List<Shop> shops = shopRepository.getByServerHash(serverHash);
        List<Long> shopIds = shops.stream().map(Shop::getId).toList();

        List<Order> orders = orderRepository.findAllByShopIdIn(shopIds);

        // Orders by shop ID
        Map<Long, List<Order>> ordersByShopID = orders.stream()
                .collect(Collectors.groupingBy(Order::getShopId));

        return shops.stream()
                .map(shop -> new ShopResponse(shop, ordersByShopID.getOrDefault(shop.getId(), List.of())))
                .toList();
    }

}

package me.shurikennen.shopboardapi.response;

import lombok.Data;
import me.shurikennen.shopboardapi.entities.Order;
import me.shurikennen.shopboardapi.entities.Shop;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShopResponse {

    private final Shop shop;
    private final List<OrderResponse> orders;


    public ShopResponse(Shop shop, List<Order> orders) {
        this.shop = shop;
        this.orders = new ArrayList<>();

        for (Order order : orders) {
            String price = order.getPrice();
            String quantity = order.getQuantity();
            this.orders.add(new OrderResponse(order.getIconId(), order.getItemName(), order.getDescription(), quantity, formatQuantity(quantity), price, formatPriceToDiamonds(price), order.isInStock()));
        }
    }

    private String formatQuantity(String quantity) {
        Integer i = tryParse(quantity);
        if (i == null) return quantity;

        if (i < 64) {
            return i.toString();
        }

        int shulkers = i / 1728;
        int remainderAfterShulker = i % 1728;
        int stacks = remainderAfterShulker / 64;
        int items = remainderAfterShulker % 64;

        List<String> parts = new ArrayList<>();

        if (shulkers > 0) {
            parts.add(shulkers + " Shulker Box" + (shulkers > 1 ? "es" : ""));
        }
        if (stacks > 0) {
            parts.add(stacks + " Stack" + (stacks > 1 ? "s" : ""));
        }
        if (items > 0) {
            parts.add(items + " Item" + (items > 1 ? "s" : ""));
        }

        String tooltip = String.join(" + ", parts);
        return quantity + " (" + tooltip + ")";
    }

    private String formatPriceToDiamonds(String price) {
        Integer i = tryParse(price);
        if (i == null) return price;

        if (i < 9) {
            return i + (i > 1 ? " Diamonds" : " Diamond");
        }

        int blocks = i / 9;
        int remainder = i % 9;

        StringBuilder sb = new StringBuilder();
        sb.append(blocks).append(" Diamond Block").append(blocks > 1 ? "s" : "");

        if (remainder > 0) {
            sb.append(" + ").append(remainder).append(" Diamond").append(remainder > 1 ? "s" : "");
        }

        // if price is higher than 9, convert tooltip to diamond blocks
        // 5 (5 Diamonds)
        // 360 (40 Diamond Blocks)
        // 370 (40 Diamond Blocks + 10 Diamonds)

        return price + " (" + sb + ")";
    }

    private Integer tryParse(String raw) {
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

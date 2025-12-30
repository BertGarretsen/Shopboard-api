package me.shurikennen.shopboardapi.response;

import lombok.Data;

@Data
public class OrderResponse {


    private final Long shopId;
    private final String iconId;
    private final String itemName;
    private final String description;
    private final String quantity;
    private final String quantityToolTip;
    private final String price;
    private final String priceToolTip;
    private final boolean inStock;


}

package me.shurikennen.shopboardapi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String server;
    private String shop;
    private String iconTexture;
    private String itemName;
    private String description;
    private String quantity;
    private String price;

    @Builder.Default
    private boolean inStock = false;

    private int posX;
    private int posZ;

}

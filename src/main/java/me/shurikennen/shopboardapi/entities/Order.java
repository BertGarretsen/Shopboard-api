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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private long shopId;
    private String iconId;
    private String itemName;
    private String description;
    private String quantity;
    private String price;

    @Column(columnDefinition = "boolean default true")
    @Builder.Default
    private boolean inStock = true;


}

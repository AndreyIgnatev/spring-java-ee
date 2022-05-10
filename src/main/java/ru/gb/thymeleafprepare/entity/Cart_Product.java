package ru.gb.thymeleafprepare.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart_Product {
    private Long id_cart;
    private Long id_product;
}

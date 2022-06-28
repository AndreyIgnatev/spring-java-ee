package ru.gb.thymeleafprepare.controller.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private Long id;
    private String status;
    private String[] arr;
}

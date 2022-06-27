package ru.gb.gbrestmart.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String buyer_details;
    private String[] product;
}

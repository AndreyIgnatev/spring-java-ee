package ru.gb.gbrestmart.controller.dto;

import lombok.*;
import ru.gb.gbrestmart.entity.Product;
import ru.gb.gbrestmart.entity.enums.Status;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

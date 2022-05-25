package ru.gb.gbrestmart.controller.dto;

import lombok.*;
import ru.gb.gbrestmart.entity.enums.Status;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
/*
@NotBlank применяется только к строкам и проверяет, что строка не пуста.
@NotNull применяется к CharSequence, Collection, Map или Array и проверяет, что объект не равен null. Но при этом он может быть пуст.
@NotEmpty применяется к CharSequence, Collection, Map или Array и проверяет, что он не null имеет размер больше 0.
Аннотация @Size(min=6) пропустит строку состоящую из 6 пробелов и/или символов переноса строки, а @NotBlank не пропустит.
*/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2)
    private BigDecimal cost;
    @PastOrPresent
    private LocalDate manufactureDate;
    @NotNull
    private Status status;
}


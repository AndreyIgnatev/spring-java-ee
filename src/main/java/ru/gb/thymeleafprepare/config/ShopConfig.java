package ru.gb.thymeleafprepare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareBean")
public class ShopConfig {

    @Bean
    public AuditorAware<String> auditorAwareBean() {
        return () -> Optional.of("User");
    }
}


/*
1. Сделайте в таблице со списком всех товаров кнопку для добавления товара в корзину.
2. Сделайте страницу для отображения всех товаров в корзине.
3. Рядом с каждым товаром в таблице списка продуктов в корзине сделать кнопку “Удалить”, при нажатии на которую товар должен быть удален из базы (удаляется связка между Cart и Product).
*/
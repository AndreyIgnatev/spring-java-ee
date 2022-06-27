package serverdz10order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareBean")
/*
 * Аннотацию @EnableFeignClients мы добавляем,
 * чтобы включить сканирование компонентов для интерфейсов, аннотированных с @FeignClient.
 */
//@EnableFeignClients(basePackageClasses = ManufacturerGateway.class)
public class ShopConfig {
    @Bean
    public AuditorAware<String> auditorAwareBean() {
        return () -> Optional.of("User");
    }
}

/*
Задание
1. Создать микросервис для работы с заказами.
Данный микросервис должен уметь создавать заказ, удалять заказ, добавлять в заказ товары, удалять товары из заказа
2. Проект приложенный в архиве запустить и коммитить по схеме описанной в схеме по работе с гитом в 8 уроке
3. Доработать код из архива gb-rest-mart так, что пользователю было предоставлено API по работе с заказом
*/
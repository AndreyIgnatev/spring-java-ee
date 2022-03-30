package ru.geekbrains.spring.dz_2;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.setOut;

@Configuration
@ComponentScan("ru.geekbrains.spring.dz_2")
public class AppConfig {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Cart cart = applicationContext.getBean(Cart.class);
        Scanner sc = new Scanner(System.in);
        int actionScanner;
        while (true){
            cart.allProductRepo();
            out.println("Программа запущена,прошу выбрать действие:\n"+"1- Добавление объекта в корзину.\n"+"2- Удаление объекта из корзины.\n"+"Список всех доступных продуктов выше!\n"+"Введите число: ");
            while (true){
                actionScanner = sc.nextInt();
                if (actionScanner == 1){
                    out.println("Можно добавить в вашу корзину товар,введите id товара: ");
                    actionScanner = sc.nextInt();
                    cart.saveProduct(actionScanner);
                    out.println("Выберете дальнейшее действие:");
                    continue;
                }
                if (actionScanner == 2){
                    out.println("Удалите товар из вашей корзины по id товара: ");
                    actionScanner = sc.nextInt();
                    cart.delProduct(actionScanner);
                    out.println("Выберете дальнейшее действие:");
                    continue;
                }

            }
        }

    }

}

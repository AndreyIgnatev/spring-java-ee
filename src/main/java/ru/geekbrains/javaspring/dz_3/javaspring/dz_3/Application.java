package ru.geekbrains.javaspring.dz_3.javaspring.dz_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 Практическое задание
 1. Разобраться с примером проекта на Spring MVC;
 2. Создать класс Товар (Product), с полями id, title, cost;
 3. Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся
 товары). Репозиторий должен уметь выдавать список всех товаров и товар по id;
 4. Сделать форму для добавления товара в репозиторий и логику работы этой формы;
 © geekbrains.ru 22
 5. Сделать страницу, на которой отображаются все товары из репозитория.
 **/


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

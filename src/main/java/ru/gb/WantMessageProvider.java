package ru.gb;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Profile("want")
@Scope("prototype")
public class WantMessageProvider implements MessageProvider {

    @InjectFoodStuff
    String foodStuff;

    @Value("${listOfFoodStuff}")
    String[] food;

    public WantMessageProvider() {
        System.out.println("foodStuff from contructor: " + foodStuff);
    }

    @PostConstruct
    public void init() {
        System.out.println("foodStuff from init: " + foodStuff);
//        for (int i = 0; i < food.length; i++) {
//            System.out.println(food[i]);
//        }
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("foodStuff from preDestroy: " + foodStuff);
    }

    @Override
    public String getMessage() {
        return "I want " + foodStuff;
    }
}

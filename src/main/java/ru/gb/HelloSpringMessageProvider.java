package ru.gb;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("hello")
public class HelloSpringMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello Spring";
    }
}

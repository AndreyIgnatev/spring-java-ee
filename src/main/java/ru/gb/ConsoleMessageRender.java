package ru.gb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class ConsoleMessageRender implements MessageRender {

    @Override
    public void render() {
        getMessageProvider().getMessage();
    }

    @Override
    @Autowired
    public void setMessageProvider(MessageProvider provider) {
    }

    @Override
    @Lookup
    public MessageProvider getMessageProvider() {
        return null;
    }
}

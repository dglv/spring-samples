package ru.dglv.springxmlcore;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static org.apache.commons.lang3.Validate.notNull;

public class App {
    private Client client;
    private Map<EventType, EventLogger> eventLoggersMap;

    private App(Client client, Map<EventType, EventLogger> eventLoggersMap) {
        this.client = client;
        this.eventLoggersMap = eventLoggersMap;
    }

    public static void main(String... args) throws InterruptedException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        App app = ctx.getBean("app", App.class);
        app.logEvent(ctx.getBean("event", Event.class), EventType.INFO, "Some event for user 1");
        Thread.sleep(1000);
        app.logEvent(ctx.getBean("event", Event.class), EventType.ERROR,"Some event for user 2");
        ctx.close();
    }

    private void logEvent(Event event, EventType eventType, String msg) {
        notNull(msg, "The message must not be null");
        notNull(eventType, "The event type must not be null");

        EventLogger logger = eventLoggersMap.get(eventType);
        if (logger == null) {
            throw new RuntimeException("Logger was NOT found for event type  " + eventType);
        }

        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        String result = eventType.name() + ' ' +
                client.getGreetingMsg() + ' ' +
                message;
        event.setMsg(result);
        logger.logEvent(event);
    }
}

package com.zanox;

import kafka.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DemoProducer {


    public static void main(String args[]) {
        DemoProducer producer = new DemoProducer();
        producer.run();
    }

    private ProducerConfig configure() {
        throw new UnsupportedOperationException();
    }

    private void run() {
        throw new UnsupportedOperationException();
    }

    private static Iterable<User.Notification> generateNotifications(int n) {
        List<User.Notification> notifications = new ArrayList<User.Notification>();
        for(int i = 0; i < n; i++) {
            notifications.add(
                    User.Notification.newBuilder()
                            .setMessageId(UUID.randomUUID().toString())
                            .setFromUser("System")
                            .setToUser("whoeever@service.com")
                            .setBody("Hello " + i)
                            .setSendOn(System.currentTimeMillis())
                            .build());
        }
        return notifications;
    }
}

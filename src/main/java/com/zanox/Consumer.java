package com.zanox;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kafka.consumer.ConsumerConfig;

/**
 * @author tonny.staunsbrink
 */
public class Consumer {
    
    public static void main(String argsp[]) {        
        Consumer consumer = new Consumer();
        consumer.run();
    }
    
    public ConsumerConfig configure() {
        throw new UnsupportedOperationException();
    }
    
    protected void run() {
        throw new UnsupportedOperationException();
    }
    
    protected Iterable<User.Notification> generateNotifications(int n) {
        List<User.Notification> notifications = new ArrayList<User.Notification>();
        for(int i = 0; i < 2; i++) {
            notifications.add(
                User.Notification.newBuilder()
                    .setMessageId(UUID.randomUUID().toString())
                    .setFromUser("System")
                    .setToUser("whoeever@service.com")
                    .setBody("Hello " + i)
                    .build());
                }
        return notifications;
    }
}

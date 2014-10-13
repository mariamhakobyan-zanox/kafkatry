package com.zanox;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class DemoProducer {

    private static kafka.javaapi.producer.Producer<String, byte[]> producer;


    public static void main(String args[]) {

        Properties props = new Properties();
        props.put("metadata.broker.list", "s-kafka-01.zanox.com:9092,s-kafka-02.zanox.com:9092 ");
        ProducerConfig config = new ProducerConfig(props);

        producer = new kafka.javaapi.producer.Producer<String, byte[]>(config);


        for(User.Notification notification : generateNotifications(5)) {
            producer.send(new KeyedMessage<String, byte[]>("trainingKafka", notification.toByteArray()));
        }
    }


    protected static Iterable<User.Notification> generateNotifications(int n) {
        List<User.Notification> notifications = new ArrayList<User.Notification>();
        for(int i = 0; i < n; i++) {
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

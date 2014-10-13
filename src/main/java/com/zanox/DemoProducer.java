package com.zanox;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class DemoProducer {


    public static void main(String args[]) {
        DemoProducer producer = new DemoProducer();
        producer.run();
    }

    private ProducerConfig configure() {
        Properties props = new Properties();
        props.put("metadata.broker.list", "s-kafka-01.zanox.com:9092,s-kafka-02.zanox.com:9092");
        props.put("request.require.acks", "1");

        return new ProducerConfig(props);
    }

    private void run() {
        ProducerConfig config = configure();

        Producer<String, byte[]> producer = new Producer<String, byte[]>(config);

        for(User.Notification notification : generateNotifications(5)) {
            producer.send(new KeyedMessage<String, byte[]>("trainingKafka", notification.toByteArray()));
        }

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

package com.zanox;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * @author tonny.staunsbrink
 */
public class DemoConsumer implements Runnable{
    
    public static void main(String args []) {
        DemoConsumer consumer = new DemoConsumer();
        Thread t = new Thread(consumer);
        t.start();
        System.out.println("Started. Reading from Kafka...");
        try {
            Thread.sleep(5000);
            System.out.println("...Done");
        } catch (InterruptedException ex) {
            // ignore
        }
        consumer.shutdown();
    }
    
    private ConsumerConnector connector;
    
    public ConsumerConfig configure() {
        Properties props = new Properties();
        props.put("group.id", "kafkaTrainingGroup");
        props.put("zookeeper.connect", "s-zk-01:2181,s-zk-02:2181,s-zk-03:2181");
        props.put("auto.commit.enable", "false");
        props.put("auto.offset.reset", "smallest");       
        return new ConsumerConfig(props);
    }
    
    @Override
    public void run() {
        connector = Consumer.createJavaConsumerConnector(configure());
        Map<String, Integer> streamConfig = new HashMap<>();
        streamConfig.put("trainingKafka", 1);        
        Map<String, List<KafkaStream<byte[], byte[]>>> topicStreams =
            connector.createMessageStreams(streamConfig);
        List<KafkaStream<byte[], byte[]>> streams = topicStreams.get("trainingKafka");
        for (KafkaStream<byte[], byte[]> stream : streams) {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while(it.hasNext()) {
                try {
                    User.Notification notification = User.Notification.parseFrom(it.next().message());
                    System.out.println(notification.toString());
                } catch (InvalidProtocolBufferException ex) {
                    System.err.println(ex.toString());
                }
            }
        }            
    }
    
    protected void shutdown() {
        if (connector != null) {
            connector.shutdown();
        }
    }
}

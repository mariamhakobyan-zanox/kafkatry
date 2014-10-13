package com.zanox;

import kafka.consumer.ConsumerConfig;

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
    
    public ConsumerConfig configure() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException();
    }
    
    protected void shutdown() {
        throw new UnsupportedOperationException();
    }
}

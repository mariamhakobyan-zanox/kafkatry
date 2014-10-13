package com.zanox;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kafka.consumer.ConsumerConfig;

/**
 * @author tonny.staunsbrink
 */
public class MyConsumer {
    
    public static void main(String argsp[]) {        
        MyConsumer consumer = new MyConsumer();
        consumer.run();
    }
    
    public ConsumerConfig configure() {
        throw new UnsupportedOperationException();
    }
    
    protected void run() {
        throw new UnsupportedOperationException();
    }
}

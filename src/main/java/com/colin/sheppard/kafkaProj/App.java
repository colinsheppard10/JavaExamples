package com.colin.sheppard.kafkaProj;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.common.io.Resources;

public class App{
    public static void main( String[] args ){
    	if(args[0].equals("producer")){
        	KafkaProducer<String, String> myProducer;
        	try {
    			InputStream myIS = Resources.getResource("producer.props").openStream();
    			Properties myProps = new Properties();
    			myProps.load(myIS);
    			myProducer = new KafkaProducer<>(myProps);
    	    	for(Integer x = 0; x < 10; x++)
    	    		myProducer.send(new ProducerRecord<String, String>("fast-messages",x.toString()));
    	    		myProducer.flush();
    	    		myProducer.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	} else if (args[0].equals("consumer")){
        	MyConsumer myConsumer = new MyConsumer();
        	myConsumer.startConsumer();
    	} else {
    		System.out.println("bad input");
    	}
    }
}

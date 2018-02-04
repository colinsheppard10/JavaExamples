package com.colin.sheppard.kafkaProj;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.google.common.io.Resources;

public class MyConsumer {
	public void startConsumer(){
		KafkaConsumer<String, String> myConsumer;
		try{
			InputStream myIS = Resources.getResource("consumer.props").openStream();
			Properties properties = new Properties();
			properties.load(myIS);
            if (properties.getProperty("group.id") == null) {
                properties.setProperty("group.id", "group-" + new Random().nextInt(100000));
            }
            myConsumer = new KafkaConsumer<>(properties);
            myConsumer.subscribe(Arrays.asList("fast-messages"));
            while(true){
            	ConsumerRecords<String, String> myRecords = myConsumer.poll(200);
            	for(ConsumerRecord<String, String> myRec : myRecords){
            		System.out.println(myRec.topic() + " - " + myRec.value());
            	}
            }
		} catch(Exception e){
			
		}
	}
}

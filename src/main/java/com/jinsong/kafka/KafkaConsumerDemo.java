package com.jinsong.kafka;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

public class KafkaConsumerDemo {

    public static void main(String[] args) {

        Properties p = new Properties();
        p.put("bootstrap.servers","localhost:9092");
        p.put("group.id","testConGro");
        p.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        p.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        p.put("auto.offset.reset","earliest");

        KafkaConsumer<String,String> kc = new KafkaConsumer<>(p);

        //订阅主题
        List<String> topics = new ArrayList<>();
        topics.add("test");
        //topics.add("__consumer_offsets");
        kc.subscribe(topics);

        //消费获取消息
        while(true) {
            ConsumerRecords<String, String> crs = kc.poll(100);

            for (ConsumerRecord<String, String> cr : crs) {
                System.out.print("1");
                System.out.print("topic:" + cr.topic());
                System.out.print(" partition:" + cr.partition());
                System.out.print(" offset:" + cr.offset());
                System.out.print(" key:" + cr.key());
                System.out.print(" value:" + cr.value());
                System.out.println("");
            }
        }


    }
}

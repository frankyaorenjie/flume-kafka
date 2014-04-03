package com.vipshop.flume;

import java.io.IOException;
import java.util.Properties;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import org.apache.flume.Context;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KafkaUtil {
    private static final Logger log = LoggerFactory.getLogger(KafkaUtil.class);

    public static String getKafkaConfigParameter(Context context, String key) {
        return context.getString(key);
    }

    public static Properties getKafkaConfigProperties(Context context) {
        Properties props = new Properties();
        ImmutableMap<String, String> parameters = context.getParameters();
        ImmutableSet<String> keys = parameters.keySet();
        UnmodifiableIterator<String> it = keys.iterator();
        while (it.hasNext()){
            String k = it.next();
            String v = parameters.get(k);
            log.info("Parse Parames: " + k + " = " + v );
            if (! k.equals("type") && !k.equals("channel")){
                props.put(k, v);
            }
        }
        log.info("PROPS:" + props);
        return props;
    }

    public static Producer<String, String> getProducer(Context context) {
        log.info(context.toString());
        Producer<String, String> producer;
        producer = new Producer<String, String>(new ProducerConfig(getKafkaConfigProperties(context)));
        return producer;
    }

    public static ConsumerConnector getConsumer(Context context) throws IOException, KeeperException, InterruptedException {
        log.info(context.toString());
        ConsumerConfig consumerConfig = new ConsumerConfig(getKafkaConfigProperties(context));
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(consumerConfig);
        return consumer;
    }
}


















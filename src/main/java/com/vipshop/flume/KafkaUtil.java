package com.vipshop.flume;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import org.apache.flume.Context;


public class KafkaUtil {

	/**
	 * @param args
	 */
	public static String getZkConnect(Context context) {
		return context.getString(KafkaConstants.CONFIG_ZK_CONNECT, "");
	}
	public static String getTopic(Context context) {
		return context.getString(KafkaConstants.CONFIG_TOPIC, "");
	}
	public static String getBatchSize(Context context) {
		return context.getString(KafkaConstants.CONFIG_BATCH_SIZE, "1");
	}
	public static Producer getProducer(Context context) {
		kafka.javaapi.producer.Producer<Integer, String> producer;
		Properties props = new Properties();
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("zk.connect", getZkConnect(context));
		props.put("producer.type", "async");
		props.put("batch.size", getBatchSize(context));
		producer = new Producer<String, byte[]>(new ProducerConfig(props));
		return producer;
	}
}

/*******************************************************************************
 * Copyright 2013 Frank Yao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.vipshop.flume;

import java.io.IOException;
import java.util.Properties;

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
	/**
	 * @param args
	 */
	public static String getKafkaConfigParameter(Context context, String key) {
		return context.getString(key);
	}
	public static Properties getKafkaConfigProperties(Context context) {
		Properties props = new Properties();
		String contextString = context.toString();
		for(final String kv : contextString.substring(14,contextString.length()-3).split(", ")) {
			String k = kv.trim().split("=")[0];
			String v = kv.trim().split("=")[1];
			log.info("Parse Parames: " + k + "=" + v);
			if (!k.equals("type") && !k.equals("channel")) {
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


















package com.vipshop.flume.sink.kafka;

import java.util.List;

import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.vipshop.flume.KafkaUtil;

public class KafkaSink extends AbstractSink implements Configurable{
	private static final Logger log = LoggerFactory.getLogger(KafkaSink.class);
	private String topic;
	private Producer<String, byte[]> producer;
	
	private final int batchSize = 100;
	
	public Status process() throws EventDeliveryException {
		Channel channel = getChannel();
		Transaction tx = channel.getTransaction();
		tx.begin();
		
		try {
			List<ProducerData<String, byte[]>> msgList = Lists.newArrayList();
			while(msgList.size() <= batchSize) {	
				Event e = channel.take();
				if(e==null) {
					break;
				}
				ProducerData<String, byte[]> msg = new ProducerData<String, byte[]>(
						this.topic,
						e.getBody());
				log.trace("Message: " + e.getBody());
				msgList.add(msg);
			}
			
			if (msgList.isEmpty()) {
				log.debug(String.format("msg-list empty"));
				tx.rollback();
				return Status.BACKOFF;
			} else {
				this.producer.send(msgList);
				log.debug(String.format("sending to kafka with batch-size %d", msgList.size()));
				tx.commit();
				return Status.READY;
			}
		} catch(Exception e) {
			tx.rollback();
			return Status.BACKOFF;
		} finally {
			tx.close();
		}
	}

	public void configure(Context context) {
		this.topic = KafkaUtil.getKafkaConfigParameter(context, "topic");
		this.producer = KafkaUtil.getProducer(context);
	}

	@Override
	public synchronized void start() {
		super.start();
	}

	@Override
	public synchronized void stop() {
		producer.close();
		super.stop();
	}
}

package com.vipshop.flume.source.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vipshop.flume.KafkaUtil;

public class KafkaSource extends AbstractSource implements Configurable, PollableSource {

	/**
	 * @param args
	 */
	private static final Logger log = LoggerFactory.getLogger(KafkaSource.class);
	ConsumerConnector consumer;
	ConsumerIterator<Message> it;
	String topic;
	Integer batchSize;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Status process() throws EventDeliveryException {
		// TODO Auto-generated method stub
		ArrayList<Event> eventList = new ArrayList<Event>();
		for(int i = 0; i < batchSize; i++){
			if(it.hasNext()) {
				log.info("-----------------has next");
				Message message = it.next().message();
				Event event = new SimpleEvent();
				event.setBody(message.toString().getBytes());
				eventList.add(event);
				log.info("----------------event list add done");
			}
		}
		getChannelProcessor().processEventBatch(eventList);
		log.info("------------------process event batch");
		return Status.READY;
	}

	public void configure(Context context) {
		// TODO Auto-generated method stub
		this.topic = KafkaUtil.getTopic(context);
		this.consumer = KafkaUtil.getConsumer(context);
		this.batchSize = Integer.parseInt(KafkaUtil.getBatchSize(context));
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(1));
		Map<String, List<KafkaStream<Message>>> consumerMap = consumer.createMessageStreams(topicCountMap);
	    KafkaStream<Message> stream =  consumerMap.get(topic).get(0);
	    it = stream.iterator();
	    log.info("-------------init it done");
	}

	@Override
	public synchronized void stop() {
		// TODO Auto-generated method stub
		log.info("-------------shutdown");
		consumer.shutdown();
		super.stop();
	}

}

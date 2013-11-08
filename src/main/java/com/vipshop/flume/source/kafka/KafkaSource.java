package com.vipshop.flume.source.kafka;

import java.io.IOException;
import java.nio.ByteBuffer;
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
import org.apache.zookeeper.KeeperException;
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
	Integer batchSize = 3;
	
	public static void main(String[] args) {

	}

	public Status process() throws EventDeliveryException {
		try {
		ArrayList<Event> eventList = new ArrayList<Event>();
		Message message;
		Event event;
		ByteBuffer buffer;
		Map<String, String> headers;
		byte [] bytes;
		for(int i = 0; i < batchSize; i++){
			if(it.hasNext()) {
				log.trace("-----------------has next");
				message = it.next().message();
				log.info("**************"+message);
				event = new SimpleEvent();
				buffer = message.payload();
				headers = new HashMap<String, String>();
				headers.put("timestamp", String.valueOf(System.currentTimeMillis()));
				bytes = new byte[buffer.remaining()];
				buffer.get(bytes);
				event.setBody(bytes);
				event.setHeaders(headers);
				log.trace(new String(bytes));
				eventList.add(event);
			}
		log.trace("----------------event list add done");
		}
		getChannelProcessor().processEventBatch(eventList);
		log.trace("------------------process event batch");
		return Status.READY;
		} catch (Exception e) {
			// TODO fix data loss while rollback
			log.debug("-----process exception: " + e);
			return Status.BACKOFF;
		}
	}

	public void configure(Context context) {
		this.topic = KafkaUtil.getKafkaConfigParameter(context, "topic");
		try {
			this.consumer = KafkaUtil.getConsumer(context);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(1));
		Map<String, List<KafkaStream<Message>>> consumerMap = consumer.createMessageStreams(topicCountMap);
	    KafkaStream<Message> stream =  consumerMap.get(topic).get(0);
	    it = stream.iterator();
	}

	@Override
	public synchronized void stop() {
		consumer.shutdown();
		super.stop();
	}

}

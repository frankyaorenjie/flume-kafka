package com.vipshop.flume.source.kafka;

import static org.junit.Assert.assertEquals;

import java.awt.List;
import java.lang.reflect.Field;

import kafka.consumer.ConsumerIterator;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource.Status;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.source.AbstractSource;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KafkaSourceTest {

	private KafkaSource kafkaSource;

	private ConsumerIterator<Message> mockit;

	private MessageAndMetadata mockMessageAndMetadata;
	private Mockery context;
	private ChannelProcessor mockChannelProcessor;
	@Before
	public void setUp() throws Exception {
		context = new Mockery() {
			{
				setImposteriser(ClassImposteriser.INSTANCE);
			}
		};
		Class.forName("kafka.consumer.ConsumerIterator");
		Class.forName("org.I0Itec.zkclient.serialize.ZkSerializer");
		mockit = context.mock(ConsumerIterator.class);
		mockMessageAndMetadata = context.mock(MessageAndMetadata.class);
		mockChannelProcessor = context.mock(ChannelProcessor.class);
		kafkaSource = new KafkaSource();
		Field field = KafkaSource.class.getDeclaredField("it");
		field.setAccessible(true);
		field.set(kafkaSource, mockit);
		
		field = KafkaSource.class.getDeclaredField("batchSize");
		field.setAccessible(true);
		field.set(kafkaSource, 20);
		
		field = AbstractSource.class.getDeclaredField("channelProcessor");
		field.setAccessible(true);
		field.set(kafkaSource, mockChannelProcessor);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStop() {
	}

	@Test
	public void testProcess() throws EventDeliveryException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		final Message message = new Message("frank".getBytes());
		Field field = MessageAndMetadata.class.getDeclaredField("message");
		field.setAccessible(true);
		field.set(mockMessageAndMetadata, message);
		context.checking(new Expectations() {
			{
				atMost(20).of(mockit).hasNext();
				atMost(20).of(mockit).next();
				will(returnValue(mockMessageAndMetadata));
				oneOf(mockChannelProcessor).processEventBatch((java.util.List<Event>) with(any(List.class)));
			}
		});
		Status status = kafkaSource.process();
		assertEquals(Status.READY, status);
		context.assertIsSatisfied();
	}
	@Test
	public void testProcess_Exception() throws EventDeliveryException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		final Message message = new Message("frank".getBytes());
		Field field = MessageAndMetadata.class.getDeclaredField("message");
		field.setAccessible(true);
		field.set(mockMessageAndMetadata, message);
		context.checking(new Expectations() {
			{
				atMost(20).of(mockit).hasNext();
				atMost(20).of(mockit).next();
				will(returnValue(mockMessageAndMetadata));
				oneOf(mockChannelProcessor).processEventBatch((java.util.List<Event>) with(any(List.class)));
				will(throwException(new Exception("processEventBatch Exception")));
			}
		});
		Status status = kafkaSource.process();
		assertEquals(Status.BACKOFF, status);
		context.assertIsSatisfied();
	}

	@Test
	public void testConfigure() {
	}

}

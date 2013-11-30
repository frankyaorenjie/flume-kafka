/*******************************************************************************
 * Copyright 2013 Renjie Yao
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.vipshop.flume.sink.kafka;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;

import org.apache.flume.Channel;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Sink.Status;
import org.apache.flume.Transaction;
import org.apache.flume.sink.AbstractSink;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaSinkTest {
	private static final Logger log = LoggerFactory.getLogger(KafkaSinkTest.class);

	private KafkaSink mockKafkaSink;
	private Producer<String, String> mockProducer;
	private Channel mockChannel;
	private Event mockEvent;
	private Transaction mockTx;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() throws Exception {
		mockProducer = mock(Producer.class);
		mockChannel = mock(Channel.class);
		mockEvent = mock(Event.class);
		mockTx = mock(Transaction.class);
		mockKafkaSink = new KafkaSink();
		
		Field field = AbstractSink.class.getDeclaredField("channel");
		field.setAccessible(true);
		field.set(mockKafkaSink, mockChannel);

		field = KafkaSink.class.getDeclaredField("topic");
		field.setAccessible(true);
		field.set(mockKafkaSink, "test");

		field = KafkaSink.class.getDeclaredField("producer");
		field.setAccessible(true);
		field.set(mockKafkaSink, mockProducer);
		
		when(mockChannel.take()).thenReturn(mockEvent);
		when(mockChannel.getTransaction()).thenReturn(mockTx);
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessStatusReady() throws EventDeliveryException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		when(mockEvent.getBody()).thenReturn("frank".getBytes());
		Status status = mockKafkaSink.process();
		verify(mockChannel, times(1)).getTransaction();
		verify(mockChannel, times(1)).take();
		verify(mockProducer, times(1)).send((ProducerData<String, String>) any());
		verify(mockTx, times(1)).commit();
		verify(mockTx, times(0)).rollback();
		verify(mockTx, times(1)).close();
		assertEquals(Status.READY, status);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessStatusBackoff() throws EventDeliveryException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		when(mockEvent.getBody()).thenThrow(new RuntimeException());
		Status status = mockKafkaSink.process();
		verify(mockChannel, times(1)).getTransaction();
		verify(mockChannel, times(1)).take();
		verify(mockProducer, times(0)).send((ProducerData<String, String>) any());
		verify(mockTx, times(0)).commit();
		verify(mockTx, times(1)).rollback();
		verify(mockTx, times(1)).close();
		assertEquals(Status.BACKOFF, status);
	}
}

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
package com.vipshop.flume.source.kafka;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import kafka.consumer.ConsumerIterator;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;

import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource.Status;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.source.AbstractSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaSourceTest {
	private static final Logger log = LoggerFactory.getLogger(KafkaSourceTest.class);


	private KafkaSource mockKafkaSource;

	private ConsumerIterator<Message> mockIt;

	@SuppressWarnings("rawtypes")
	private MessageAndMetadata mockMessageAndMetadata;
	private ChannelProcessor mockChannelProcessor;
	private ByteBuffer mockBuffer;
	private Message mockMessage;
	
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup() throws Exception {
		mockIt = mock(ConsumerIterator.class);
		mockMessageAndMetadata = mock(MessageAndMetadata.class);
		mockChannelProcessor = mock(ChannelProcessor.class);
		mockBuffer = mock(ByteBuffer.class);
		mockMessage = mock(Message.class);
		mockKafkaSource = new KafkaSource();
		
		when(mockMessage.payload()).thenReturn(mockBuffer);
		when(mockMessageAndMetadata.message()).thenReturn(mockMessage);
		
		Field field = AbstractSource.class.getDeclaredField("channelProcessor");
		field.setAccessible(true);
		field.set(mockKafkaSource, mockChannelProcessor);

		field = KafkaSource.class.getDeclaredField("it");
		field.setAccessible(true);
		field.set(mockKafkaSource, mockIt);
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessItNotEmpty() throws EventDeliveryException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		when(mockIt.next()).thenReturn(mockMessageAndMetadata);
		when(mockIt.hasNext()).thenReturn(true);
		Status status = mockKafkaSource.process();
		verify(mockIt, times(1)).hasNext();
		verify(mockIt, times(1)).next();
		verify(mockChannelProcessor, times(1)).processEventBatch(anyList());
		when(mockIt.next()).thenReturn(mockMessageAndMetadata);
		assertEquals(Status.READY, status);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessItEmpty() throws EventDeliveryException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		when(mockIt.next()).thenReturn(mockMessageAndMetadata);
		when(mockIt.hasNext()).thenReturn(false);
		Status status = mockKafkaSource.process();
		verify(mockIt, times(1)).hasNext();
		verify(mockIt, times(0)).next();
		verify(mockChannelProcessor, times(1)).processEventBatch(anyList());
		when(mockIt.next()).thenReturn(mockMessageAndMetadata);
		assertEquals(Status.READY, status);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessException() throws EventDeliveryException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		when(mockIt.next()).thenThrow(new RuntimeException());
		when(mockIt.hasNext()).thenReturn(true);
		Status status = mockKafkaSource.process();
		verify(mockIt, times(1)).hasNext();
		verify(mockIt, times(1)).next();
		verify(mockChannelProcessor, times(0)).processEventBatch(anyList());
		assertEquals(Status.BACKOFF, status);
	}
}

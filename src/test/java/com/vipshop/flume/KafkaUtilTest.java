package com.vipshop.flume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Properties;

import org.apache.flume.Context;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KafkaUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetKafkaConfigParameter() {
		Context context = new Context();
		context.put("agent.sink.kafka1.consumer.timeout", "10");
		context.put("agent.sink.kafka1.type", "KafkaSource");
		Properties props = KafkaUtil.getKafkaConfigProperties(context);
		assertEquals("10",props.getProperty("kafka1.consumer.timeout"));
		assertNull(props.getProperty("kafka1.type"));
	}

	@Test
	public void testGetKafkaConfigProperties() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetProducer() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetConsumer() {
//		fail("Not yet implemented");
	}

}

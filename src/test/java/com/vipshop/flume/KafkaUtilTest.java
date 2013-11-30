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
		context.put("consumer.timeout", "10");
		context.put("type", "KafkaSource");
		context.put("topic", "test");
		Properties props = KafkaUtil.getKafkaConfigProperties(context);
		assertEquals("10",props.getProperty("consumer.timeout"));
		assertEquals("test",props.getProperty("topic"));
		assertNull(props.getProperty("type"));
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

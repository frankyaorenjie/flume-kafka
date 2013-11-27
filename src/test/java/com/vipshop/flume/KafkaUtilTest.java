package com.vipshop.flume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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
		context.put("k", "v");
		assertEquals("v",KafkaUtil.getKafkaConfigParameter(context, "k"));
		assertNull(KafkaUtil.getKafkaConfigParameter(context, null));
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

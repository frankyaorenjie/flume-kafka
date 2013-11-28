import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.put("metadata.broker.list", "127.0.0.1:9092");
		props.put("producer.type", "async");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		Producer<String, String> producer = new Producer<String, String>(
				new ProducerConfig(props));
		producer.send(new KeyedMessage<String, String>("test", "test"));
		producer.close();
	}

}

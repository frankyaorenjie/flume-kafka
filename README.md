Flume-ng Kafka
===========

This project is used for [flume-ng](https://github.com/apache/flume) to communicate with [kafka 0.7,2](http://kafka.apache.org/07/quickstart.html).

For v0.2 now, I think the parameters pass to flume-kafka need to be handled by users, not by code. Before this version, I add many parameters of kafka and their default value in code. That is to say, whatever parameters you write in conf file, they will be passed to Kafka producer or consumers. I cannot control if the parameters you wrote will take effect. The responsibilites for using correct parameters or find out what parameters to use, in my opinion, are yours. 

On the other hand, it is simple if Kafka add some new parameters:).

Configuration of Kafka Sink
----------

    agent_log.sinks.kafka_all.type = com.vipshop.flume.sink.kafka.KafkaSink
    agent_log.sinks.kafka_all.channel = all_channel
    agent_log.sinks.kafka_all.zkconnect = 127.0.0.1:2181
    agent_log.sinks.kafka_all.topic = all
    agent_log.sinks.kafka_all.batchsize = 200
    agent_log.sinks.kafka0.producer.type = async
    agent_log.sinks.kafka0.serializer.class = kafka.serializer.StringEncoder


Configuration of Kafka Source
----------

    agent_log.sources.kafka.type = com.vipshop.flume.source.kafka.KafkaSource
    agent_log.sources.kafka.channels = internal_channel
    agent_log.sources.kafka.zkconnect = 127.0.0.1:2181
    agent_log.sources.kafka.batchsize = 200
    agent_log.sources.kafka.topic = all
    agent_log.sources.kafka.group = hdfs

Performance Workbench
----------

I have run performance test. One is tail file to kafka and the other is fetch message from kafka. In test I used memory channel in flume, both kafka sink and kafka source can reach 150,000 message per second (when I added pressure to 200,000 message per second, memory channel would have pending message). While the performance hit this awesome performace, I think this plugin for flumg-ng can hold most condition.

Speical Thanks
---------

In fact I'm a newbie in Java. I have learnt a lot from [flumg-ng-rabbitmq](https://github.com/jcustenborder/flume-ng-rabbitmq). Thanks to [Jeremy Custenborder](https://github.com/jcustenborder).

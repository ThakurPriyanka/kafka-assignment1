package edu.knoldus

import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.log4j.Logger

import scala.collection.JavaConverters._


class CustomConsumer {
  val log = Logger.getLogger(this.getClass)
  def readToKafka(topic: String) = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "edu.knoldus.utils.PersonDeserializer")
    props.put("group.id", "something")
    props.put("auto.offset.reset", "earliest")
    props.put("enable.auto.commit", "false ")
    val consumer = new KafkaConsumer[String, Person](props)
    consumer.subscribe(java.util.Collections.singletonList(topic))
    while (true) {
      val records = consumer.poll(5000)
      for (record <- records.asScala)
        log.info(record.value()) }

  }

}

object ConsumerApplication extends App {

  val topic = "test"

  (new CustomConsumer).readToKafka(topic)

}
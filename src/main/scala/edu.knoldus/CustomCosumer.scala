package edu.knoldus

import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._

class CustomConsumer {

  def readToKafka(topic: String) = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "something")
    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(java.util.Collections.singletonList(topic))
    while (true) {
      val records = consumer.poll(5000)
      for (record <- records.asScala)
        println(record.value()) }

  }

}

object ConsumerApplication extends App {

  val topic = "test"

  (new CustomConsumer).readToKafka(topic)

}
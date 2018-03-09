package edu.knoldus

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}


class CustomProducer {
  def writetoKafka(topic: String) {
    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)
    val topic = "test"
    for (i <- 1 to 1000) {
      val record = new ProducerRecord[String, String](topic, "key", i.toString())
      producer.send(record)
    }
    producer.close()

  }
}

object ProducerApplication extends App {

  val topic = "test"
  (new CustomProducer).writetoKafka(topic)

}
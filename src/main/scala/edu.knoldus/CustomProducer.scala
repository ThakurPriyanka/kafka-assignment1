package edu.knoldus

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.log4j.Logger



class CustomProducer {
  def writetoKafka(topic: String) {
    val props = new Properties()

    val ROLLNUMBER = 50
    val person: Person = Person("Priyanka", 23)
    val TIMEOUT = 5000
    val log = Logger.getLogger(this.getClass)
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "util.PersonSerializer")

    val producer = new KafkaProducer[String, Person](props)
    val topic = "test"
    for (i <- 1 to 10) {
      val record = new ProducerRecord[String, Person](topic, "key", person)
      producer.send(record)
    }
    producer.close()

  }
}

object ProducerApplication extends App {

  val topic = "test"
  (new CustomProducer).writetoKafka(topic)

}
package edu.knoldus.util

import org.apache.kafka.common.serialization.Deserializer
import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util.Map

import edu.knoldus.Person

class PersonDeserializer extends Deserializer[Person]{


    override def configure(configs: Map[String, _], isKey: Boolean): Unit = {}

    override def deserialize(topic: String, bytes: Array[Byte]): Person = {
      val byteIn = new ByteArrayInputStream(bytes)
      val objIn = new ObjectInputStream(byteIn)
      val obj = objIn.readObject().asInstanceOf[Person]
      byteIn.close()
      objIn.close()
      obj
    }

    override def close(): Unit = {}

}

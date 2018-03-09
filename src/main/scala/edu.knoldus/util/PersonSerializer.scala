package edu.knoldus.util


import org.apache.kafka.common.serialization.Serializer
import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util

import edu.knoldus.Person

class PersonSerializer extends Serializer[Person] {


  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}


  override def serialize(topic: String, data: Person): Array[Byte] = {
    try {
      val byteOut = new ByteArrayOutputStream()
      val objOut = new ObjectOutputStream(byteOut)
      objOut.writeObject(data)
      objOut.close()
      byteOut.close()
      byteOut.toByteArray
    }
    catch {
      case ex: Exception => throw new Exception(ex.getMessage)
    }
  }

  override def close(): Unit = {}

}

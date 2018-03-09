package edu.knoldus

case class Person(name: String, age: Int) {
  override def toString: String = {
    s"$name : $age"
  }
}

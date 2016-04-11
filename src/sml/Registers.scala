package sml

/**
  * Created by sush on 19/02/2016.
  */
case class Registers(size: Int) {
  val registers: Array[Int] = new Array(size)

  override def toString(): String =
    registers.mkString(" ")

  def update(k: Int, v: Int) = registers(k) = v
  def apply(k: Int) = registers(k)
}

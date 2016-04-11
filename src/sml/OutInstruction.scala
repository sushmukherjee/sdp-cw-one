package sml

/**
  * Created by sush on 27/02/2016.
  */
/** The print instruction.
  *
  * @constructor creates a new print instruction defining it's label and register.
  * @param label the instruction's label
  * @param opCode the operation code for the instruction
  * @param register the register holding the value to be printed
  */

case class OutInstruction(label: String, opCode: String, register: Int) extends Instruction(label, opCode) {

  /** Executes the instruction.
    *
    * Retrieves the value from the register in the given machine, and
    * prints the value to the console.
    * @param m The machine object on which the instruction should be run.
    */
  override def execute(m: Machine) {
    val value = m.regs(register)
    println(value)
  }

  /** A toString method depicting the instruction.
    *
    * @return an unambiguous string, which reflects the given instruction in the standard format.
    */
  override def toString(): String = {
    super.toString + " " + register + "\n"
  }
}

object OutInstruction {
  def apply(label: String, register: Int) = {
    new OutInstruction(label, "out", register)
  }
}
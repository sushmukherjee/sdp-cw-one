package sml

/**
  * Created by sush on 27/02/2016.
  */
/** The subtraction instruction.
  *
  * @constructor creates a new subtraction instruction defining it's label and registers.
  * @param label the instruction's label
  * @param opCode the operation code for the instruction
  * @param result the register where the result should be stored
  * @param op1 the register where the first operand can be found
  * @param op2 the register where the second operand can be found
  */
case class SubInstruction(label: String, opCode: String, result: Int, op1: Int, op2: Int) extends Instruction(label, opCode) {

  /** Executes the instruction.
    *
    * Retrieves the operands from the registers in the given machine, and
    * performs the operation saving the result in the result register.
    * @param m The machine object on which the instruction should be run.
    */
  override def execute(m: Machine) {
    val value1 = m.regs(op1)
    val value2 = m.regs(op2)
    m.regs(result) = value1 - value2
  }

  /** A toString method depicting the instruction.
    *
    * @return an unambiguous string, which reflects the given instruction in the standard format.
    */
  override def toString(): String = {
    super.toString + " " + op1 + " - " + op2 + " to " + result + "\n"
  }
}

object SubInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) = {
    new SubInstruction(label, "sub", result, op1, op2)
  }
}

package sml

/**
  * Created by sush on 27/02/2016.
  */


/** The division instruction.
  *
  * @constructor creates a new division instruction defining it's label and registers.
  * @param label the instruction's label
  * @param opCode the operation code for the instruction
  * @param result the register where the result should be stored
  * @param op1 the register where the first operand can be found
  * @param op2 the register where the second operand can be found
  */
case class DivInstruction(label: String, opCode: String, result: Int, op1: Int, op2: Int) extends Instruction(label, opCode) {

  /** Executes the instruction.
    *
    * Retrieves the operands from the registers in the given machine, and
    * performs the operation saving the result in the result register.
    * @param m The machine object on which the instruction should be run.
    */
  override def execute(m: Machine) {
    val value1 = m.regs(op1)
    val value2 = m.regs(op2)
    try {
      m.regs(result) = value1 / value2
    } catch {
      case arithEx: ArithmeticException => println("\nInput errors detected in code file, please correct errors and " +
        "run program again.\n\nINPUT ERROR: Cannot divide by zero with 'div' instruction on line '" + super.toString() +
        " " + result + " " + op1 + " " + op2 + "'\n")
        throw arithEx
    }
  }

  /** A toString method depicting the instruction.
    *
    * @return an unambiguous string, which reflects the given instruction in the standard format.
    */
  override def toString(): String = {
    super.toString + " " + op1 + " / " + op2 + " to " + result + "\n"
  }
}


object DivInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) = {
    new DivInstruction(label, "div", result, op1, op2)
  }
}
package sml

/**
  * Created by sush on 27/02/2016.
  */
/** The 'jump to' instruction.
  *
  * @constructor creates a new 'jump to' instruction defining its labels and register
  * @param label1 the instructions label
  * @param opCode the operation code for the instruction
  * @param register if this register equals 0 the instruction will not jump.
  * @param label2 the label of the instruction to be jumped to.
  */
case class BnzInstruction(label1: String, opCode: String, register: Int, label2: String) extends Instruction(label1, opCode) {

  /** Executes the instruction.
    *
    * Retrieves the operands from the registers in the given machine, and
    * performs the operation jumping to the given insturction if the value
    * in the register is anything other than zero.
    * @param m The machine object on which the instruction should be run.
    */
  override def execute(m: Machine) {

    if (m.regs(register) != 0) {
      m.execute(m.labels.labels.indexOf(label2))
    }
    //TODO: create an error free version of this method if/when possible within the confines of the assignment. See README file for more details.

  }

  /** A toString method depicting the instruction.
    *
    * @return an unambiguous string, which reflects the given instruction in the standard format.
    */
  override def toString(): String = {
    super.toString + " " + register + " jump to " + label2 + "\n"
  }
}

object BnzInstruction {
  def apply(label1: String, register: Int, label2: String) = {
    new BnzInstruction(label1, "bnz", register, label2)
  }
}

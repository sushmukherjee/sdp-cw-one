package sml
import scala.collection.mutable.ListBuffer

/**
  * Created by sush on 19/02/2016.
  */

class Translator(fileName: String) {

  def readAndTranslate(m: Machine): Machine = {
    var labels = m.labels
    var program = m.prog
    import scala.io.Source
    val lines = Source.fromFile(fileName).getLines
    for (line <- lines) {
      var fields = line.split(" ")
      if (fields.length > 0) {
        try {
          val instructionClass = Class.forName("sml." + fields(1).capitalize + "Instruction")
          val runtimeUniverse = scala.reflect.runtime.universe
          val runtimeMirror = runtimeUniverse.runtimeMirror(getClass.getClassLoader)
          val classSymbol = runtimeMirror.classSymbol(instructionClass)
          val classMirror = runtimeMirror.reflectClass(classSymbol)
          val constructorSymbol =  classSymbol.primaryConstructor.asMethod
          val constructorInstruction = classMirror.reflectConstructor(constructorSymbol).symbol.asMethod
          val constructorMirror = classMirror.reflectConstructor(constructorInstruction)
          val constructorParams = constructorInstruction.paramLists
          var instructionParams = new ListBuffer[Any]()
          var counter = 0
          for (paramsList <- constructorParams) {
            for (param <- paramsList) {
              param.info.toString match {
                case "String" => instructionParams.append(fields(counter))
                case "Int" => instructionParams.append(fields(counter).toInt)
                case x => {
                  println("Invalid param type for instruction: " + x)
                  throw new Exception()
                }
              }
              counter += 1
            }
          }
          labels.add(fields(0))
          val temporaryInstruction = constructorMirror.apply(instructionParams: _*).asInstanceOf[sml. Instruction]
          program = program :+ temporaryInstruction
        } catch {
          case caseNotFoundException: java.lang.ClassNotFoundException => println("Illegal Instruction " + fields(1) + ", not implementing this Instruction")
        }
      }
    }
    new Machine(labels, program)
  }
}

object Translator {
  private val directory: String = "src/"

  def apply(file: String) =
    new Translator(directory + file)
}
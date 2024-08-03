class Calculator {
  var inputSequence: String = ""
  var currentInput: String = ""

  def add(a: Double, b: Double): Double = a + b
  def subtract(a: Double, b: Double): Double = a - b
  def multiply(a: Double, b: Double): Double = a * b
  def divide(a: Double, b: Double): Double = if (b != 0) a / b else Double.NaN
  def sqrt(a: Double): Double = Math.sqrt(a)
  def cubeRoot(a: Double): Double = Math.cbrt(a)

  def calculate(): String = {
    val tokens = inputSequence.split(" ")
    if (tokens.length == 3) {
      val a = tokens(0).toDouble
      val operator = tokens(1)
      val b = tokens(2).toDouble
      operator match {
        case "+" => add(a, b).toString
        case "-" => subtract(a, b).toString
        case "*" => multiply(a, b).toString
        case "/" => divide(a, b).toString
        case _ => "Error"
      }
    } else {
      "Error"
    }
  }

  def calculateSqrt(): String = {
    if (currentInput.nonEmpty) {
      val number = currentInput.toDouble
      clear()
      sqrt(number).toString
    } else {
      "Error"
    }
  }

  def calculateCubeRoot(): String = {
    if (currentInput.nonEmpty) {
      val number = currentInput.toDouble
      clear()
      cubeRoot(number).toString
    } else {
      "Error"
    }
  }

  def inputDigit(digit: String): Unit = {
    currentInput += digit
    inputSequence += digit
  }

  def inputOperator(operator: String): Unit = {
    if (currentInput.nonEmpty) {
      inputSequence += s" $operator "
      currentInput = ""
    }
  }

  def clear(): Unit = {
    inputSequence = ""
    currentInput = ""
  }
}

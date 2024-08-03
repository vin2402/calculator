import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField, TextArea, Label}
import scalafx.scene.layout.{VBox, GridPane}
import scalafx.geometry.Pos
import scalafx.scene.paint.Color
import scalafx.scene.text.Font

object CalculatorApp extends JFXApp {

  val calculator = new Calculator
  val history = new History

  val display = new TextField {
    editable = false
    style = "-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 36px; -fx-alignment: center-right;"
    maxWidth = Double.MaxValue
    font = Font.font("Arial", 36)
  }

  val historyDisplay = new TextArea {
    editable = false
    style = "-fx-background-color: black; -fx-text-fill: black; -fx-font-size: 14px; -fx-border-color: gray; -fx-border-width: 1px;"
    maxWidth = Double.MaxValue
    maxHeight = 100
  }

  val calculationDisplay = new Label {
    style = "-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 24px; -fx-alignment: center-right;"
    maxWidth = Double.MaxValue
    font = Font.font("Arial", 24)
  }

  def handleInput(input: String): Unit = {
    input match {
      case digit if "0123456789".contains(digit) =>
        calculator.inputDigit(digit)
        display.text = calculator.currentInput
        calculationDisplay.text = calculator.currentInput
      case operator if "+-*/".contains(operator) =>
        calculator.inputOperator(operator)
        display.text = calculator.inputSequence
        calculationDisplay.text = calculator.inputSequence
      case "C" =>
        calculator.clear()
        display.text = ""
        calculationDisplay.text = ""
      case "=" =>
        val result = calculator.calculate()
        display.text = result
        calculationDisplay.text = s"${calculator.inputSequence} = $result"
        history.addEntry(s"${calculator.inputSequence} = $result")
        historyDisplay.text = history.getEntries.mkString("\n")
      case "√" =>
        if (calculator.currentInput.nonEmpty) {
          val number = calculator.currentInput
          val result = calculator.calculateSqrt()
          display.text = result
          calculationDisplay.text = s"√($number) = $result"
          history.addEntry(s"√($number) = $result")
          historyDisplay.text = history.getEntries.mkString("\n")
        }
      case "∛" =>
        if (calculator.currentInput.nonEmpty) {
          val number = calculator.currentInput
          val result = calculator.calculateCubeRoot()
          display.text = result
          calculationDisplay.text = s"∛($number) = $result"
          history.addEntry(s"∛($number) = $result")
          historyDisplay.text = history.getEntries.mkString("\n")
        }
      case _ =>
    }
  }

  def createButton(text: String, bgColor: String = "#4e4e5a", textColor: String = "white"): Button = {
    new Button(text) {
      style = s"-fx-background-color: $bgColor; -fx-text-fill: $textColor; -fx-font-size: 24px; -fx-border-color: red; -fx-border-width: 1px;"
      prefWidth = 70
      prefHeight = 70
      onAction = _ => handleInput(text())
    }
  }

  stage = new JFXApp.PrimaryStage {
    title = "Calculator"
    scene = new Scene {
      fill = Color.Black
      root = new VBox {
        style = "-fx-background-color: black;"
        spacing = 10
        alignment = Pos.Center
        children = Seq(
          display,
          calculationDisplay,
          new GridPane {
            style = "-fx-background-color: black;"
            hgap = 5
            vgap = 5
            alignment = Pos.Center
            add(createButton("7"), 0, 0)
            add(createButton("8"), 1, 0)
            add(createButton("9"), 2, 0)
            add(createButton("+"), 3, 0)
            add(createButton("4"), 0, 1)
            add(createButton("5"), 1, 1)
            add(createButton("6"), 2, 1)
            add(createButton("-"), 3, 1)
            add(createButton("1"), 0, 2)
            add(createButton("2"), 1, 2)
            add(createButton("3"), 2, 2)
            add(createButton("*"), 3, 2)
            add(createButton("0"), 0, 3)
            add(createButton("√"), 1, 3)
            add(createButton("∛"), 2, 3)
            add(createButton("/"), 3, 3)
            add(createButton("C", "#3a3a46"), 1, 4)
            add(createButton("=", "#ff5a36"), 2, 4)
          },
          historyDisplay // History display positioned below the buttons
        )
      }
    }
  }
}

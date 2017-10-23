package gui

import exec.runner

import scala.swing._
import scala.swing.event.ButtonClicked

/**
 * Created by ltrogr on 10/25/14.
 */
object controlPanel {

  def setThreadCount:Unit = {
    threadCountLabel.text = "Thread count: " + runner.threadCount.toString
  }

  val threadCountLabel = new Label

  val buttonAddThread = new Button {
    text = "+"
    borderPainted = true
    enabled = true
    tooltip = "Add thread"
  }

  val buttonDeleteThread = new Button {
    text = "-"
    borderPainted = true
    enabled = true
    tooltip = "Remove thread"
  }

  val contents = new FlowPanel() {
    contents += threadCountLabel
    contents += buttonAddThread
    contents += buttonDeleteThread

    listenTo(buttonAddThread,buttonDeleteThread)

    reactions += {
      case ButtonClicked(component) if component == buttonAddThread => runner.threadCountInc()
      case ButtonClicked(component) if component == buttonDeleteThread => runner.threadCountDec()
    }
  }



}

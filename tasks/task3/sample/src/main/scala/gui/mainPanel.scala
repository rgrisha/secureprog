package gui

/**
 * Created by ltrogr on 10/25/14.
 */

import scala.swing._

object mainPanel extends SimpleSwingApplication {

  def setThreadCount:Unit = {
    controlPanel.setThreadCount
  }

  def top = new MainFrame {
    title = "Multithreaded primary digit checker"

    bounds = new Rectangle(300,300,300,300)
    minimumSize = new Dimension(500, 500)

    //contents = new GridPanel(3,1) {
    contents = new BoxPanel(Orientation.Vertical) {
      contents += controlPanel.contents
      contents += statsPanel.contents
      contents += threadInfoPanel.panelContents
    }

  }

}

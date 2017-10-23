package gui

import scala.swing._

/**t
 * Created by ltrogr on 10/28/14.
 */
class ThreadInfoItem {

  val infoArea = new TextArea
  val progressBar = new ProgressBar {
    min = 0
  }

  val contents = new BoxPanel(Orientation.Vertical) {
    border = Swing.EmptyBorder(2,2,2,2)
    contents += infoArea
    contents += progressBar
  }

  def getContents = contents

  def setInfo(strInfo:String) = {
    infoArea.text_=(strInfo)
  }

  def setProgress(hows:Int) = {
    progressBar.value = hows
  }

  def setProgressMax(maxProgress:Int) = {
    progressBar.max = maxProgress
  }

}

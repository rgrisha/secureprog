package gui

import java.awt.Color
import javax.swing.BorderFactory

import scala.swing.{TextArea, FlowPanel}

/**
 * Created by ltrogr on 10/26/14.
 */
object statsPanel {

  def updateStatistics(newStat:String):Unit = {
    stats.text = newStat
  }

  val stats = new TextArea {
    text = ""
  }

  val contents = new FlowPanel {
    contents += stats
    border = BorderFactory.createLineBorder(Color.black);
  }


}

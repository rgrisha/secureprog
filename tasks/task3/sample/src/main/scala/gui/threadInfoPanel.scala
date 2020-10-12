package gui

import scala.swing.{Orientation, BoxPanel}

/**
 * Created by ltrogr on 10/27/14.
 */
object threadInfoPanel {

  var panelMap: Map[Int, ThreadInfoItem] = Map()

  val panelContents = new BoxPanel(Orientation.Vertical)

  def addNewItem(panelId: Int) = {
    val threadInfoItem = new ThreadInfoItem
    panelMap += (panelId -> threadInfoItem)
    panelContents.contents += threadInfoItem.getContents
  }

  def deleteItem(panelId: Int) = {
    val panel = getPanel(panelId)
    panelContents.contents -= panel.getContents
  }

  def getPanel(panelId:Int) = panelMap(panelId)
}


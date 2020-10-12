package exec

import gui.statsPanel

/**
 * Created by ltrogr on 10/26/14.
 */
object statsHolder {

  var maxPrimeFound = 0
  var minPrimeFound = Int.MaxValue
  var lastFileProcessed = ""
  var filesProcessed = 0

  def setStatistics(minPrime:Int, maxPrime:Int, fileName:String) = {
    if(minPrime < minPrimeFound) {
      minPrimeFound = minPrime
      lastFileProcessed = fileName
    }
    if(maxPrime > maxPrimeFound) {
      maxPrimeFound = maxPrime
      lastFileProcessed = fileName
    }
    filesProcessed += 1

    statsPanel.updateStatistics(toString())
  }

  override def toString =
    "Minimal prime found: " + minPrimeFound + "\n" +
    "Maximal prime found: " + maxPrimeFound + "\n" +
    "Last updating file: " + lastFileProcessed + "\n" +
    "Files processed: " + filesProcessed

}

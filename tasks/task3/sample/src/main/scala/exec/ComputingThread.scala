package exec

import gui.threadInfoPanel

import scala.io.Source

/**
 * Created by ltrogr on 10/29/14.
 */
class ComputingThread(threadIdNumber:Int, fileName: String) {

  var minPrime = Int.MaxValue
  var maxPrime = 0

  def getFileName = fileName
  def getIdNumber = threadIdNumber

  def primesUnder(n: Int): List[Int] = {
    require(n >= 2)

    def rec(i: Int, primes: List[Int]): List[Int] = {
      if (i >= n) primes
      else if (isPrime(i, primes)) rec(i + 1, i :: primes)
      else rec(i + 1, primes)
    }

    rec(2, List()).reverse
  }

  def isPrime(num: Int, factors: List[Int]): Boolean = factors.forall(num % _ != 0)

  def isPrimeSimple(i:Int) : Boolean = {
     if (i <= 1) false
     else if (i == 2) true
     else !(2 to (i-1)).exists(x => i % x == 0)
  }

  def primesComputation() = {
    val linesIterator = Source.fromFile(fileName).getLines
    val lines = linesIterator.toList

    val infoPanel = threadInfoPanel.getPanel(threadIdNumber)
    var linesDone = 0
    var primesFound = 0

    infoPanel.setProgressMax(lines.length)
    infoPanel.setInfo(fileName)

    //System.out.println("File " + fileName + " lines: " + lines.length)

    lines.foreach {line =>
      System.out.println("Got number from line: " + line)
      val number = Integer.parseInt(line)
      //val primesUnderCalculated = primesUnder(number)
      //if(isPrime(number,primesUnderCalculated)) {
      if(isPrimeSimple(number)) {
        primesFound = primesFound + 1
        infoPanel.setInfo(fileName + " (" + primesFound + ")")
        if(number < minPrime) {minPrime = number}
        if(number > maxPrime) {maxPrime = number}
      }

      linesDone = linesDone + 1
      infoPanel.setProgress(linesDone)
    }

  }
}

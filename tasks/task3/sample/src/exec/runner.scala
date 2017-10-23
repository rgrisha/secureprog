package exec

import java.util
import java.util.concurrent.{TimeUnit, LinkedBlockingQueue, BlockingQueue, ConcurrentLinkedQueue}
import gui.{statsPanel, threadInfoPanel, mainPanel}
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.swing.Dialog

/**
 * Created by ltrogr on 10/25/14.
 */
object runner {

  var _threadCount: Int = 4
  var realThreadCount: Int = 0
  var threadsDone: Int = 0
  //val outputQueue: ConcurrentLinkedQueue[ComputingThread] = new ConcurrentLinkedQueue[ComputingThread]()
  val outputQueue: BlockingQueue[ComputingThread] = new LinkedBlockingQueue[ComputingThread]()
  var inputQueue: ConcurrentLinkedQueue[String] = new ConcurrentLinkedQueue[String]()
  //val threads = new util.ArrayList[ComputingThread]

  def threadCount = _threadCount
  def threadCount_= (newThreadCount:Int):Unit = _threadCount=newThreadCount

  def threadCountInc() = {
    _threadCount = _threadCount + 1
    mainPanel.setThreadCount
  }

  def threadCountDec() = {
    if(_threadCount > 1) {
      _threadCount = _threadCount - 1
      mainPanel.setThreadCount
    }
  }

  def threadsDoneInc() = { threadsDone = threadsDone + 1}

  def readFileNames(directoryPath:String) = {
    val files = new java.io.File(directoryPath).listFiles.filter(_.getName.endsWith(".txt")).map(_.getAbsolutePath)
    files.foreach(s=>inputQueue.add(s))
  }

  def runNewThread() = {
    val computingThread = new ComputingThread(threadsDone, inputQueue.poll())
    //threads.add(computingThread)
    threadInfoPanel.addNewItem(threadsDone)
    threadsDoneInc()

    val futurePrimeResult = Future {
      computingThread.primesComputation
      computingThread
    }

    futurePrimeResult onSuccess {
      case computingThread => {
        outputQueue.add(computingThread)
      }
    }
  }

  def threadManager = {
    while(inputQueue.size() > 0) {
      if( realThreadCount < threadCount ) {
        runNewThread()
        realThreadCount = realThreadCount + 1
      }
      val threadResult = outputQueue.poll(100, TimeUnit.MILLISECONDS)
      if(threadResult != null) {
        realThreadCount = realThreadCount - 1
        statsHolder.setStatistics(threadResult.minPrime, threadResult.maxPrime,threadResult.getFileName)
        statsPanel.updateStatistics(statsHolder.toString)
        threadInfoPanel.deleteItem(threadResult.getIdNumber)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    if(args.length < 1) {
      Dialog.showMessage(null, "No params supplied", title = "Error")
      return
    }

    readFileNames(args.head)
    mainPanel.top.open()
    mainPanel.setThreadCount
    //statsPanel.updateStatistics("read files: " + inputQueue.size().toString)

    threadManager
  }


}

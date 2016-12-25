package dugu9sword.maze

import java.io.File
import java.util.Scanner

/**
  * Created by zhouyi on 2016/12/22.
  */
class Maze {

    var structure: Array[Array[Int]] = _
    var currentX = 1
    var currentY = 1

    var gameOver = false

    def loadMaze(filepath: String) = {
        val scanner = new Scanner(new File(filepath))
        val row = scanner.nextLine.toInt
        val col = scanner.nextLine.toInt
        structure = Array.ofDim[Int](row, col)
        for (i <- 0 until row) {
            val s = scanner.nextLine
            for (j <- 0 until col)
                structure(i)(j) = s(j) - '0'
        }
    }

    def init(x: Int, y: Int): Unit = {
        currentX = x
        currentY = y
        gameOver = false
    }

    def transit(action: Action.Value): Float = {
        action match {
            case Action.LEFT => currentY -= 1
            case Action.RIGHT => currentY += 1
            case Action.UP => currentX -= 1
            case Action.DOWN => currentX += 1
        }
        var reward: Float = 0
        if (currentX < 0 || currentX >= structure.length ||
          currentY < 0 || currentY >= structure(0).length ||
          structure(currentX)(currentY) == 1) {
            reward = -10
            gameOver = true
        } else if (currentX == structure.length - 2 && currentY == structure(0).length - 2) {
            reward = 10
            gameOver = true
        } else
            reward = 0
        //        println(currentX + "," + currentY + ":" + reward)
        return reward
    }

    override def toString: String = {
        val sb = new StringBuilder
        for (row <- structure) {
            for (col <- row)
                sb.append(col)
            sb.append("\n")
        }
        return sb.toString
    }
}

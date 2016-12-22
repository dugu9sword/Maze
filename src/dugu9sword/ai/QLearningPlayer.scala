package dugu9sword.ai

import dugu9sword.maze._

import scala.util.Random

/**
  * Created by zhouyi on 2016/12/22.
  */
class QLearningPlayer extends Player {

    val gamma = 0.8f
    var Q: Array[Array[Array[Float]]] = _

    override def play(maze: Maze): Action.Value = {
        val qs = Q(maze.currentX)(maze.currentY)
        var maxValue: Float = qs(0)
        var maxIndex: Int = 0
        for (i <- 0 until 4)
            if (qs(i) > maxValue) {
                maxValue = qs(i)
                maxIndex = i
            }
        return Action.toAction(maxIndex)
    }

    override def train(maze: Maze): Unit = {
        val ROW = maze.structure.length
        val COL = maze.structure(0).length
        //    println(ROW+" "+COL)
        Q = Array.ofDim[Float](ROW, COL, 4)
        for (episode <- 0 until 10000) {
            maze.init(Random.nextInt(ROW), Random.nextInt(COL))
            if (maze.structure(maze.currentX)(maze.currentY) == 0)
                singleEpisode(maze)
        }
    }

    def singleEpisode(maze: Maze): Unit = {
        println("Training at position " + maze.currentX + "," + maze.currentY)
        while (!maze.gameOver) {
            val action = Action.toAction(Random.nextInt(4))
            println("Choosing action " + action)
            val curX = maze.currentX
            val curY = maze.currentY
            val R = maze.transit(action)
            val nextX = maze.currentX
            val nextY = maze.currentY
            println("Now position " + maze.currentX + "," + maze.currentY)
            Q(curX)(curY)(Action.toIndex(action)) = R + gamma * Q(nextX)(nextY).max
            println("Q at " + curX + "," + curY + "," + action + " is updated to " + Q(curX)(curY)(Action.toIndex(action)))
        }
    }
}

package dugu9sword

import dugu9sword.ai.QLearningPlayer
import dugu9sword.maze.Maze

/**
  * Created by zhouyi on 2016/12/22.
  */
object Main {
    def main(args: Array[String]): Unit = {
        val maze = new Maze
        maze.loadMaze("mazes/SAMPLE")
        print(maze.toString)
        val player = new QLearningPlayer
        player.train(maze)

        for (i <- 0 until 4) {
            println(dugu9sword.maze.Action.toAction(i))
            for (r <- 0 until player.Q.length) {
                for (c <- 0 until player.Q(0).length)
                    print(player.Q(r)(c)(i).toInt + "\t")
                println()
            }
        }

        maze.init(5, 3)
        while (!maze.gameOver) {
            val action = player.play(maze)
            maze.transit(action)
            println(action)
        }

    }
}

package dugu9sword.maze

/**
  * Created by zhouyi on 2016/12/22.
  */
abstract class Player {
    def play(maze: Maze): Action.Value

    def train(maze: Maze)
}

package dugu9sword.maze

/**
  * Created by zhouyi on 2016/12/22.
  */
object Action extends Enumeration {
    //  type Action = Value
    val LEFT, RIGHT, UP, DOWN = Value

    def toIndex(action: Action.Value): Int = {
        action match {
            case LEFT => return 0
            case RIGHT => return 1
            case UP => return 2
            case DOWN => return 3
        }
    }

    def toAction(int: Int): Action.Value = {
        int match {
            case 0 => return LEFT
            case 1 => return RIGHT
            case 2 => return UP
            case 3 => return DOWN
        }
    }

}

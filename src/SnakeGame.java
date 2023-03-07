import java.util.*;

public class SnakeGame {

    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 20;

    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;

    private static int direction;
    private static int score;
    private static boolean gameOver;

    public static void main(String[] args) {
        System.out.println("Welcome to my snake game!");
    }

}
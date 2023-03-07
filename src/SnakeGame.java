import java.util.*;

public class SnakeGame {

    private static final int BOARD_WIDTH = 15;
    private static final int BOARD_HEIGHT = 15;
    private static int[][] grid;
    private static int posX = 20;
    private static int posY = 12;

    // private static final int UP = 1;
    // private static final int DOWN = 2;
    // private static final int LEFT = 3;
    // private static final int RIGHT = 4;

    private static int direction;
    private static int score;
    private static boolean gameOver;

    public static void main(String[] args) {
        System.out.println("Welcome to my snake game!");

        // ici je vais essayer de print out une grid
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                if (i == 0 || i == BOARD_WIDTH - 1 || j == 0 || j == BOARD_HEIGHT - 1) {
                    System.out.print("# ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

}
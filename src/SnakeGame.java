import java.util.*;

public class SnakeGame {

    private static final int BOARD_WIDTH = 15;
    private static final int BOARD_HEIGHT = 15;
    private static char[][] grid = new char[BOARD_WIDTH][BOARD_HEIGHT];
    private static int posX;
    private static int posY;
    private static int score;
    private static boolean gameOver;

    public static void main(String[] args) {
        // on initialise la grid avec du vide (spaces)
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                grid[j][i] = ' ';
            }
        }
        System.out.println("Welcome to my snake game!");
        posX = 5;
        posY = 5;
        Display(posX, posY);
        int a = 0;
        while (a < 5) {

            try {
                posX++;
                Thread.sleep(1000);
                grid[posX - 1][posY] = ' ';
                clearScreen();
                Display(posX, posY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a++;
        }

    }

    private static void Display(int x, int y) {
        grid[x][y] = 'S';
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                if (i == 0 || i == BOARD_WIDTH - 1 || j == 0 || j == BOARD_HEIGHT - 1) {
                    System.out.print("# ");
                } else {
                    System.out.print(grid[j][i] + " ");
                }
            }
            System.out.println();
        }

    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
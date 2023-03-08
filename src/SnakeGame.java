import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class SnakeGame extends JFrame implements KeyListener {

    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 20;
    private static final int SQUARE_SIZE = 20;
    private static final int PADDING = 5;
    private static Color[][] grid = new Color[BOARD_WIDTH][BOARD_HEIGHT];
    private static int posX;
    private static int posY;
    private static int score;
    private static String direction = "right";
    private static boolean gameOver;

    public SnakeGame() {
        this.setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("Snake Game!");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);

        // Make the frame visible
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        // loop through each row and column and draw a rectangle
        for (int row = 0; row < BOARD_WIDTH; row++) {
            for (int col = 0; col < BOARD_HEIGHT; col++) {
                int x = col * (SQUARE_SIZE + PADDING); // x-coordinate of top-left corner
                int y = row * (SQUARE_SIZE + PADDING); // y-coordinate of top-left corner
                if (row == 0 || row == BOARD_WIDTH - 1 || col == 0 || col == BOARD_HEIGHT - 1) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }
                // g.setColor(grid[row][col]); // set color from the 2D array
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        SnakeGame snek = new SnakeGame();

        // // on initialise la grid avec du vide (spaces)
        // for (int i = 0; i < BOARD_WIDTH; i++) {
        // for (int j = 0; j < BOARD_HEIGHT; j++) {
        // grid[j][i] = ' ';
        // }
        // }
        // console.append("Welcome to my snake game!\n");
        posX = 5;
        posY = 5;
        // Display(posX, posY);
        int a = 0;
        // try {
        // while (!gameOver) {
        // Thread.sleep(1000);
        // if (direction == "right") {
        // posX++;
        // grid[posX - 1][posY] = ' ';
        // clearScreen();
        // Display(posX, posY);
        // }
        // if (direction == "down") {
        // posY++;
        // grid[posX][posY - 1] = ' ';
        // clearScreen();
        // Thread.sleep(1000);
        // Display(posX, posY);
        // }
        // if (direction == "left") {
        // posX--;
        // grid[posX + 1][posY] = ' ';
        // clearScreen();
        // Thread.sleep(1000);
        // Display(posX, posY);
        // }
        // if (direction == "up") {
        // posY--;
        // grid[posX][posY + 1] = ' ';
        // clearScreen();
        // Thread.sleep(1000);
        // Display(posX, posY);
        // }
        // }
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // while (a < 10) {

        // try {
        // posX++;
        // Thread.sleep(1000);
        // grid[posX - 1][posY] = ' ';
        // clearScreen();
        // Display(posX, posY);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // a++;
        // }

    }

    // private static void Display(int x, int y) {
    // grid[x][y] = 'S';
    // for (int i = 0; i < BOARD_WIDTH; i++) {
    // for (int j = 0; j < BOARD_HEIGHT; j++) {
    // if (i == 0 || i == BOARD_WIDTH - 1 || j == 0 || j == BOARD_HEIGHT - 1) {
    // // console.append("# ");
    // } else {
    // // console.append(grid[j][i] + " ");
    // }
    // }
    // // console.append("\n");
    // // console.append("" + console.getColumns());
    // }

    // }

    private static void clearScreen() {
        // console.setText("");

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed : " + e.getKeyCode());
        // 65 =a
        // 87 = w
        // 68 = d
        // 83 =s
        if (e.getKeyCode() == 65) {
            direction = "left";
            // console.append(direction + "\n");
        }
        if (e.getKeyCode() == 87) {
            direction = "up";
            // console.append(direction + "\n");
        }
        if (e.getKeyCode() == 68) {
            direction = "right";
            // console.append(direction + "\n");
        }
        if (e.getKeyCode() == 83) {
            direction = "down";
            // console.append(direction + "\n");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Key released: " + e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Key typed: " + e.getKeyCode());
    }

}
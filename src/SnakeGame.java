import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class SnakeGame extends JFrame implements KeyListener {

    private static final int BOARD_WIDTH = 20;
    private static SnakeGame snek;
    private static final int BOARD_HEIGHT = 20;
    private static final int SQUARE_SIZE = 20;
    private static final int PADDING = 5;
    private static Color[][] grid = new Color[BOARD_WIDTH][BOARD_HEIGHT];
    private static int[] zeSnek = new int[] { BOARD_WIDTH / 2, BOARD_HEIGHT / 2 };
    private static int posX;
    private static int posY;
    private static int speed = 1;
    private static int score;
    private static String direction = "right";
    private static boolean gameOver = false;

    public SnakeGame() {
        this.setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("Snake Game!");
        setFocusable(true);
        addKeyListener(this);

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
                } else if (row == zeSnek[0] && col == zeSnek[1]) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.BLACK);
                }
                // g.setColor(grid[row][col]); // set color from the 2D array
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        snek = new SnakeGame();

        while (true) {
            // wait a short amount of time
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(direction);
            moveSnek();
            snek.repaint();
        }

    }

    private static void moveSnek() {
        int oldX = zeSnek[0];
        int oldY = zeSnek[1];
        if (direction == "right") {
            zeSnek[1] += speed;
            System.out.println(zeSnek[0]);
            snek.repaint(zeSnek[0], zeSnek[1], SQUARE_SIZE, SQUARE_SIZE);
        } else if (direction == "left") {
            zeSnek[1] -= speed;
            snek.repaint(zeSnek[0], zeSnek[1], SQUARE_SIZE, SQUARE_SIZE);
        } else if (direction == "up") {
            zeSnek[0] -= speed;
            snek.repaint(zeSnek[0], zeSnek[1], SQUARE_SIZE, SQUARE_SIZE);
        } else if (direction == "down") {
            zeSnek[0] += speed;
            snek.repaint(zeSnek[0], zeSnek[1], SQUARE_SIZE, SQUARE_SIZE);
        }

        // check if blue square went out of bounds
        if (zeSnek[0] < PADDING || zeSnek[0] > BOARD_WIDTH * (SQUARE_SIZE + PADDING)
                ||
                zeSnek[1] < PADDING || zeSnek[1] > BOARD_HEIGHT * (SQUARE_SIZE + PADDING)) {
            // reset blue square to center of board
            zeSnek[1] = BOARD_WIDTH / 2 * (SQUARE_SIZE + PADDING);
            zeSnek[0] = BOARD_HEIGHT / 2 * (SQUARE_SIZE + PADDING);
        }
        // update the grid with the new locations of the squares
        int row = oldY / (SQUARE_SIZE + PADDING);
        int col = oldX / (SQUARE_SIZE + PADDING);
        grid[row][col] = Color.BLACK;

        row = zeSnek[1] / (SQUARE_SIZE + PADDING);
        col = zeSnek[0] / (SQUARE_SIZE + PADDING);
        if (grid[row][col] == Color.BLUE) {
            System.out.println("Game Over");
            // set game over to true and break out of loop
            gameOver = true;
            return;
        }
        grid[row][col] = Color.BLUE;
    }

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
        // System.out.println("Key typed: " + e.getKeyCode());
    }

}
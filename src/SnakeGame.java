import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class SnakeGame extends JFrame implements ActionListener, KeyListener {

    private static SnakeGame snek;
    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 20;
    private static final int SQUARE_SIZE = 20;
    private static final int PADDING = 5;
    private static Color[][] grid = new Color[BOARD_WIDTH][BOARD_HEIGHT];
    private static int[] zeSnek = new int[] { BOARD_WIDTH / 2, BOARD_HEIGHT / 2 };
    private static ArrayList<int[]> snakebody = new ArrayList<>();
    private static int[] food = new int[] { 15, 10 };
    private static int posX;
    private static int posY;
    private static int speed = 1;
    private static int score;
    private static Timer timer;
    private static String direction;
    private static boolean[][] borders = new boolean[BOARD_WIDTH][BOARD_HEIGHT];

    private static boolean gameOver = false;

    public SnakeGame() {
        this.setSize(800, 800);
        setLocationRelativeTo(null);
        setTitle("Snake Game!");
        setFocusable(true);
        addKeyListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);

        snakebody.add(0, new int[] { zeSnek[0], zeSnek[1] });
        // Make the frame visible
        setVisible(true);

    }

    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("paint!");

        // loop through each row and column and draw a rectangle
        for (int row = 0; row < BOARD_WIDTH; row++) {
            for (int col = 0; col < BOARD_HEIGHT; col++) {
                int x = col * (SQUARE_SIZE + PADDING) + 50; // x-coordinate of top-left corner
                int y = row * (SQUARE_SIZE + PADDING) + 50; // y-coordinate of top-left corner
                boolean isSnakeBody = false;
                for (int i = 0; i < snakebody.size(); i++) {
                    int[] part = snakebody.get(i);
                    if (col == part[0] && row == part[1]) {
                        g.setColor(Color.BLUE);
                        g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                        isSnakeBody = true;
                        break;
                    }
                }
                if (!isSnakeBody) {
                    if (row == food[1] && col == food[0]) {
                        g.setColor(Color.RED);
                    } else if (borders[row][col]) {
                        g.setColor(Color.GREEN);
                    } else {
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);

                }
                // if (row == food[1] && col == food[0]) {
                // g.setColor(Color.RED);
                // } else if (borders[row][col]) {
                // g.setColor(Color.GREEN);
                // } else if (row == snakebody.get(0)[1] && col == snakebody.get(0)[0]) {
                // g.setColor(Color.BLUE);
                // } else {
                // g.setColor(Color.WHITE);
                // }
                // g.setColor(grid[row][col]); // set color from the 2D array
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        snek = new SnakeGame();
        // loop through each row and column and mark the border squares
        for (int row = 0; row < BOARD_WIDTH; row++) {
            for (int col = 0; col < BOARD_HEIGHT; col++) {
                if (row == 0 || row == BOARD_WIDTH - 1 || col == 0 || col == BOARD_HEIGHT - 1) {
                    borders[row][col] = true;
                }
            }
        }
        int delay = 500; // milliseconds
        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // System.out.println(direction);
                moveSnek();
                // System.out.printf("head: (%d, %d)", snakebody.get(0)[0],
                // snakebody.get(0)[1]);
                // System.out.println();
                snek.repaint();
            }
        });
        timer.start();
        while (!gameOver) {
            // todo
        }

    }

    private static void moveSnek() {
        int oldX = snakebody.get(0)[0];
        int oldY = snakebody.get(0)[1];
        if (direction == "right") {
            snakebody.get(0)[0] += speed;
            System.out.println(snakebody.get(0)[0]);
            // snek.repaint(snakebody.get(0)[0], snakebody.get(0)[1], SQUARE_SIZE,
            // SQUARE_SIZE);
        } else if (direction == "left") {
            snakebody.get(0)[0] -= speed;
            // snek.repaint(snakebody.get(0)[0], snakebody.get(0)[1], SQUARE_SIZE,
            // SQUARE_SIZE);
        } else if (direction == "up") {
            snakebody.get(0)[1] -= speed;
            // snek.repaint(snakebody.get(0)[0], snakebody.get(0)[1], SQUARE_SIZE,
            // SQUARE_SIZE);
        } else if (direction == "down") {
            snakebody.get(0)[1] += speed;
            // snek.repaint(snakebody.get(0)[0], snakebody.get(0)[1], SQUARE_SIZE,
            // SQUARE_SIZE);
        }

        // check if blue square went out of bounds
        if (borders[snakebody.get(0)[0]][snakebody.get(0)[1]]) {
            System.out.println("touch!");
            timer.stop();
        }

        // ici c'est surement le bout le plus important de tout le programme
        // update the snake body
        int[] newBodyPart = { oldX, oldY };
        snakebody.add(1, newBodyPart);
        snakebody.remove(snakebody.size() - 1);

        if (snakebody.get(0)[0] == food[0] && snakebody.get(0)[1] == food[1]) {
            System.out.println("yum!");
            // we add a square in the snake at the end
            snakebody.add(snakebody.size() - 1, new int[] { food[0], food[1] });
            // vu que on a manger, on bouge le food
            // snek.repaint(snakebody.get(snakebody.size() - 1)[0],
            // snakebody.get(snakebody.size() - 1)[1], SQUARE_SIZE, SQUARE_SIZE);
            MoveFood();
        }

        // update the grid with the new locations of the squares
        int row = oldY / (SQUARE_SIZE + PADDING);
        int col = oldX / (SQUARE_SIZE + PADDING);
        grid[row][col] = Color.BLACK;
        // snek.repaint(zeSnek[0], zeSnek[1], SQUARE_SIZE, SQUARE_SIZE);

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

    private static void MoveFood() {
        // snek.repaint();
        Random rand = new Random();
        int foodX = (int) (Math.random() * (18 - 1)) + 1;
        int foodY = (int) (Math.random() * (18 - 1)) + 1;
        // if (foodX <= 0 || foodX >= 19) {

        // foodX = rand.nextInt(BOARD_WIDTH + 1) - 1;
        // }
        food[0] = foodX;
        food[1] = foodY;
        System.out.printf("food: (%d, %d)", food[0], food[1]);
        System.out.println();
        snek.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed : " + e.getKeyCode());
        // 65 =a
        // 80 =p
        // 87 = w
        // 68 = d
        // 83 =s
        if (e.getKeyCode() == 80) {
            // direction = "left";
            System.exit(0);
            // console.append(direction + "\n");
        }
        if (e.getKeyCode() == 70) {
            // direction = "left";
            MoveFood();
            // console.append(direction + "\n");
        }
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
        // System.out.println("Key released: " + e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("Key typed: " + e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
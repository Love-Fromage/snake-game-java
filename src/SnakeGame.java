import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class SnakeGame extends JFrame implements KeyListener {
    private static JTextArea console;

    public SnakeGame() {
        this.setSize(700, 500);
        setTitle("Snake Game!");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a new JTextArea to display console output
        console = new JTextArea();
        console.setEditable(false);

        // Add the JTextArea to a JScrollPane and add it to the frame
        getContentPane().add(console);
        this.getContentPane().setBackground(Color.BLACK);
        console.setBackground(Color.BLACK);
        console.setForeground(Color.GREEN);
        // Add Keylistener // Add KeyListener to JTextArea
        console.addKeyListener(this);
        console.setColumns(55);
        console.setRows(55);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed : " + e.getKeyCode());
    }

    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 20;
    private static char[][] grid = new char[BOARD_WIDTH][BOARD_HEIGHT];
    private static int posX;
    private static int posY;
    private static int score;
    private static boolean gameOver;

    public static void main(String[] args) {
        SnakeGame snek = new SnakeGame();
        // on initialise la grid avec du vide (spaces)
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                grid[j][i] = ' ';
            }
        }
        console.append("Welcome to my snake game!\n");
        posX = 5;
        posY = 5;
        Display(posX, posY);
        int a = 0;
        while (a < 10) {

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
                    console.append("# ");
                } else {
                    console.append(grid[j][i] + "  ");
                }
            }
            console.append("\n");
            // console.append("" + console.getColumns());
        }

    }

    private static void clearScreen() {
        console.setText("");

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
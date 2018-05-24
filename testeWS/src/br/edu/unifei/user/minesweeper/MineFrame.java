package br.edu.unifei.user.minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class MineFrame {

    //time the game was paused for
    private static double pauseTime = 0.0;
    private static double startPauseTime = 0;

    //Declare GUI objects
    private static JFrame frame;
    private static JPanel gamePanel;
    private static JLabel statusbar;

    //Generic int[] stacks
    public static Stack<int[]> undoStack = new Stack<int[]>();
    public static Stack<int[]> redoStack = new Stack<int[]>();

    //Declare static integers so that they can be accessed from static getters and setters.
    private static int noOfMines = 40;
    private static int noOfRows = 24;
    private static int noOfCols = 24;

    //Static boolean to be accessed across all classes
    public static boolean playingGame;

    //Static long which will contain the time a game has started in milliseconds
    private static long startTime;

    //Init width and height for the gamePanel
    private static int height;
    private static int width;

    //Declare the menu bar and its items (GUI elements)
    private static JMenuItem pauseItem;

    private JRadioButtonMenuItem beginnerItem, intermediateItem, expertItem;

    //Constructor of the MineFrame
    public MineFrame() {
        buildFrame();
    }

    public void buildFrame() {
        frame = new JFrame();//Create the frame for the GUI

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Have the application exit when closed
        frame.setTitle("Minesweeper");//Title of the frame
        frame.setResizable(false);//Have the frame re-sizable useful for custom games

        statusbar = new JLabel("teste");//Set the passed-in status bar
        gamePanel = new JPanel(new BorderLayout());//New panel that contains the board
        frame.add(gamePanel);//Add gamePanel to the frame
        startNewGame();
        frame.setVisible(true);//Show all components on the window
    }

    //Method to start/restart the game when a game has been lost, restarted or loaded
    public static void startNewGame() {
        gamePanel.removeAll();
        undoStack.removeAllElements();
        redoStack.removeAllElements();
        gamePanel.add(statusbar, BorderLayout.SOUTH);
        gamePanel.add(new Board(statusbar, noOfMines, noOfRows, noOfCols), BorderLayout.CENTER);

        playingGame = true;//Set to true so the user may make actions
        startTime = System.currentTimeMillis(); //save the time the game started

        calcDimentions();
        gamePanel.setPreferredSize(new Dimension(width, height));
        gamePanel.validate();
        gamePanel.repaint();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - width) / 2);
        int y = (int) ((dimension.getHeight() - height - 100) / 2);
        frame.setLocation(x, y);
        frame.pack();
    }

    //Accessors and mutators
    //Accessor for the number of mines
    public static int getNoOfMines() {
        return noOfMines;
    }

    //Mutator for the number of mines
    public static void setNoOfMines(int noOfMines) {
        MineFrame.noOfMines = noOfMines;
    }

    //Accessor for the number of columns
    public static int getNoOfCols() {
        return noOfCols;
    }

    //Mutator for the number of columns
    public static void setNoOfCols(int noOfCols) {
        MineFrame.noOfCols = noOfCols;
    }

    //Accessor for the number of rows
    public static int getNoOfRows() {
        return noOfRows;
    }

    //Mutator for the number of rows
    public static void setNoOfRows(int noOfRows) {
        MineFrame.noOfRows = noOfRows;
    }

    //Setter for width and height
    public static void setWidth(int width) {
        MineFrame.width = width;
    }

    public static void setHeight(int height) {
        MineFrame.height = height;
    }

    //Method that returns the time elapsed from the time a game was started
    public static double getCurrentTime() {
        double endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000.0;
    }

    public static void timePause() {
        if (playingGame) {
            startPauseTime = System.currentTimeMillis();
        }

        if (!playingGame) {
            double endPauseTime = System.currentTimeMillis();
            pauseTime += (endPauseTime - startPauseTime) / 1000.0;
        }
    }

    //Method that returns the score (total time - paused time)
    public static double getScore() {
        return getCurrentTime() - pauseTime;
    }

    public static void calcDimentions() {
        width = noOfCols * 15;
        height = noOfRows * 15 + 20;
    }

}

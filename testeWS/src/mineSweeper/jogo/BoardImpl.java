/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.jogo;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Cesar
 */
public class BoardImpl extends java.rmi.server.UnicastRemoteObject implements Board {

    private int NUM_IMAGES = 13;
    private int CELL_SIZE = 15;

    public static int COVER_FOR_CELL = 10;
    private int MARK_FOR_CELL = 10;
    private int EMPTY_CELL = 0;
    private int MINE_CELL = 9;
    private int COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;
    private int MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;

    private int DRAW_MINE = 9;
    private int DRAW_COVER = 10;
    private int DRAW_MARK = 11;
    private int DRAW_WRONG_MARK = 12;

    private static int[] field;
    private static boolean inGame;
    private int mines_left;
    private Image[] img;
    private int mines;
    private static int rows;
    private static int cols;
    private int all_cells;
    private JLabel statusbar;

    private String mineStr = "Mines left: ";

    private int difficulty;
    private boolean solved = false;

    private boolean host;
    private Board serverBoard;
    private boolean rep = false;

    public BoardImpl() throws RemoteException {
        super();
    }

    public BoardImpl(JLabel statusbar, int noOfMines, int noOfRows, int noOfCols, boolean host) throws RemoteException{
        this.statusbar = statusbar;
        mines = noOfMines;
        rows = noOfRows;
        cols = noOfCols;
        this.host = host;
        this.startNewGame();
    }

    // set solved (mutator/setter)
    public void setSolved(boolean newState) {
        solved = newState;
    }

    // set inGame (mutator/setter)
    public void setInGame(boolean newState) {
        inGame = newState;
    }

    //set difficulty (mutator/setter)
    public void setDifficulty(int newDifficulty) {
        difficulty = newDifficulty;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        BoardImpl.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        BoardImpl.cols = cols;
    }

    public int getMines_left() {
        return mines_left;
    }

    public void setMines_left(int mines_left) {
        this.mines_left = mines_left;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getAll_cells() {
        return all_cells;
    }

    public void setAll_cells(int all_cells) {
        this.all_cells = all_cells;
    }

    public int getCOVER_FOR_CELL() {
        return COVER_FOR_CELL;
    }

    public void setCOVER_FOR_CELL(int COVER_FOR_CELL) {
        BoardImpl.COVER_FOR_CELL = COVER_FOR_CELL;
    }

    public int getMARK_FOR_CELL() {
        return MARK_FOR_CELL;
    }

    public void setMARK_FOR_CELL(int MARK_FOR_CELL) {
        this.MARK_FOR_CELL = MARK_FOR_CELL;
    }

    public int getEMPTY_CELL() {
        return EMPTY_CELL;
    }

    public void setEMPTY_CELL(int EMPTY_CELL) {
        this.EMPTY_CELL = EMPTY_CELL;
    }

    public int getMINE_CELL() {
        return MINE_CELL;
    }

    public void setMINE_CELL(int MINE_CELL) {
        this.MINE_CELL = MINE_CELL;
    }

    public int getCOVERED_MINE_CELL() {
        return COVERED_MINE_CELL;
    }

    public void setCOVERED_MINE_CELL(int COVERED_MINE_CELL) {
        this.COVERED_MINE_CELL = COVERED_MINE_CELL;
    }

    public int getMARKED_MINE_CELL() {
        return MARKED_MINE_CELL;
    }

    public void setMARKED_MINE_CELL(int MARKED_MINE_CELL) {
        this.MARKED_MINE_CELL = MARKED_MINE_CELL;
    }

    public int getDRAW_MINE() {
        return DRAW_MINE;
    }

    public void setDRAW_MINE(int DRAW_MINE) {
        this.DRAW_MINE = DRAW_MINE;
    }

    public int getDRAW_COVER() {
        return DRAW_COVER;
    }

    public void setDRAW_COVER(int DRAW_COVER) {
        this.DRAW_COVER = DRAW_COVER;
    }

    public int getDRAW_MARK() {
        return DRAW_MARK;
    }

    public void setDRAW_MARK(int DRAW_MARK) {
        this.DRAW_MARK = DRAW_MARK;
    }

    public int getDRAW_WRONG_MARK() {
        return DRAW_WRONG_MARK;
    }

    public void setDRAW_WRONG_MARK(int DRAW_WRONG_MARK) {
        this.DRAW_WRONG_MARK = DRAW_WRONG_MARK;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public int getNUM_IMAGES() {
        return NUM_IMAGES;
    }

    public int getCELL_SIZE() {
        return CELL_SIZE;
    }

    public Image[] getImg() {
        return img;
    }

    public boolean isInGame() {
        return inGame;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isSolved() {
        return solved;
    }

    public JLabel getStatusbar() {
        return statusbar;
    }

    public boolean isRep() {
        return rep;
    }

    //Gets the field and returns it (getter)
    @Override
    public int[] getField() throws RemoteException {
        return field;
    }

    //Sets the field with a new array (mutator/setter)
    public void setField(int[] arr) {
        field = arr;
    }

    // Push the array 'field' into the undoStack
    public static void pushFieldToUndoStack() {
        //Push the array 'field' into the undoStack
        MineFrame.undoStack.push(field.clone());
    }

    private String getService(String serverIp, int port) {
        return "rmi://" + serverIp + ":" + port + "/RemoteGameService";
    }

    public void startNewGame() {

        //Declare image array
        img = new Image[NUM_IMAGES];

        //Load images into img
        for (int i = 0; i < NUM_IMAGES; i++) {
            img[i] = (new ImageIcon(this.getClass().getResource((i) + ".png"))).getImage();
        }

    }

    public void newGame() {
        if (host) {
            Random random;
            int current_col;

            int i = 0;
            int position = 0;
            int cell = 0;

            random = new Random();
            inGame = true;
            mines_left = mines;

            //Assign the amount of cells there are to all_cells
            all_cells = rows * cols;

            //Assign 'field' the size of all_cells
            field = new int[all_cells];

            //Assign cover cell image to all cells on the board
            for (i = 0; i < all_cells; i++) {
                field[i] = COVER_FOR_CELL;
            }

            //Set the text for the status bar
            statusbar.setText(mineStr + Integer.toString(mines_left));

            //Reset i to 0
            i = 0;
            while (i < mines) {
                //Select a random cell on the board and place a mine in it
                position = (int) (all_cells * random.nextDouble());

                if ((position < all_cells) && (field[position] != COVERED_MINE_CELL)) {

                    current_col = position % cols;
                    field[position] = COVERED_MINE_CELL;
                    i++;

                    if (current_col > 0) {
                        cell = position - 1 - cols;
                        if (cell >= 0) {
                            if (field[cell] != COVERED_MINE_CELL) {
                                field[cell] += 1;
                            }
                        }

                        cell = position - 1;

                        if (cell >= 0) {
                            if (field[cell] != COVERED_MINE_CELL) {
                                field[cell] += 1;
                            }
                        }

                        cell = position + cols - 1;

                        if (cell < all_cells) {
                            if (field[cell] != COVERED_MINE_CELL) {
                                field[cell] += 1;
                            }
                        }
                    }

                    cell = position - cols;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }

                    cell = position + cols;

                    if (cell < all_cells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }

                    if (current_col < (cols - 1)) {
                        cell = position - cols + 1;

                        if (cell >= 0) {
                            if (field[cell] != COVERED_MINE_CELL) {
                                field[cell] += 1;
                            }
                        }

                        cell = position + cols + 1;

                        if (cell < all_cells) {
                            if (field[cell] != COVERED_MINE_CELL) {
                                field[cell] += 1;
                            }
                        }

                        cell = position + 1;

                        if (cell < all_cells) {
                            if (field[cell] != COVERED_MINE_CELL) {
                                field[cell] += 1;
                            }
                        }
                    }
                }
            }

        } else {
            try {
                serverBoard = (Board) Naming.lookup(getService("192.168.100.126", 7879));
                field = serverBoard.getField();
            } catch (RemoteException ex) {
                Logger.getLogger(BoardJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(BoardJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(BoardJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // save first undo to stack
        pushFieldToUndoStack();
    }

    // search & uncover cell when there isn't a mine around it
    public void find_empty_cells(int j) {

        int current_col = j % cols;
        int cell;

        if (current_col > 0) {
            cell = j - cols - 1;
            if (cell >= 0) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j - 1;
            if (cell >= 0) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + cols - 1;
            if (cell < all_cells) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

        cell = j - cols;
        if (cell >= 0) {
            if (field[cell] > MINE_CELL) {
                field[cell] -= COVER_FOR_CELL;
                if (field[cell] == EMPTY_CELL) {
                    find_empty_cells(cell);
                }
            }
        }

        cell = j + cols;
        if (cell < all_cells) {
            if (field[cell] > MINE_CELL) {
                field[cell] -= COVER_FOR_CELL;
                if (field[cell] == EMPTY_CELL) {
                    find_empty_cells(cell);
                }
            }
        }

        if (current_col < (cols - 1)) {
            cell = j - cols + 1;
            if (cell >= 0) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + cols + 1;
            if (cell < all_cells) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + 1;
            if (cell < all_cells) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }
        }
    }

    @Override
    public void processClick(int x, int y, int button) throws RemoteException {

        int cCol = x / CELL_SIZE;
        int cRow = y / CELL_SIZE;

//        if (!inGame) {
//            MineFrame.startNewGame();
//        }
        if ((x < cols * CELL_SIZE) && (y < rows * CELL_SIZE) && MineFrame.playingGame) {

            //Rightmouse button - set flag and update statusbar
            if (button == MouseEvent.BUTTON3) {

                if (field[(cRow * cols) + cCol] > MINE_CELL) {
                    rep = true;

                    if (field[(cRow * cols) + cCol] <= COVERED_MINE_CELL) {
                        if (mines_left > 0) {
                            field[(cRow * cols) + cCol] += MARK_FOR_CELL;
                            mines_left--;
                            statusbar.setText(mineStr + Integer.toString(mines_left));
                        } else {
                            statusbar.setText("No marks left");
                        }
                    } else {
                        field[(cRow * cols) + cCol] -= MARK_FOR_CELL;
                        mines_left++;
                        statusbar.setText(mineStr + Integer.toString(mines_left));
                    }
                }

            } else {
                //Nothing happens when we click on a marked cell
                if (field[(cRow * cols) + cCol] > COVERED_MINE_CELL) {
                    return;
                }

                if ((field[(cRow * cols) + cCol] > MINE_CELL) && (field[(cRow * cols) + cCol] < MARKED_MINE_CELL)) {

                    field[(cRow * cols) + cCol] -= COVER_FOR_CELL;
                    rep = true;

                    if (field[(cRow * cols) + cCol] == MINE_CELL) {
                        inGame = false;
                    }
                    if (field[(cRow * cols) + cCol] == EMPTY_CELL) {
                        find_empty_cells((cRow * cols) + cCol);
                    }
                }
            }

        }
    }

}

package mineSweeper.jogo;

import mineSweeper.jogo.MineFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
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
import javax.swing.JPanel;

public class BoardJPanel extends JPanel {

    private BoardImpl localBoard;

    //Constructor
    public BoardJPanel() {
    }

    public BoardJPanel(JLabel statusbar, int noOfMines, int noOfRows, int noOfCols, boolean host) {
        try {
            //Set the values of the member variables as determined by the MineFrame class
            localBoard = new BoardImpl(statusbar, noOfMines, noOfRows, noOfCols, host);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        localBoard.newGame();
        setDoubleBuffered(true);

        addMouseListener(new MinesAdapter());

    }

    public BoardImpl getLocalBoard() {
        return localBoard;
    }

    public void setLocalBoard(BoardImpl localBoard) {
        this.localBoard = localBoard;
    }
    

    @Override
    public void paint(Graphics g) {

        int cell = 0;
        int uncover = 0;
        int[] field = null;
        int rows = localBoard.getRows();
        int cols = localBoard.getCols();
        boolean inGame = localBoard.isInGame();
        int MINE_CELL = localBoard.getMINE_CELL();
        int COVERED_MINE_CELL = localBoard.getCOVERED_MINE_CELL();
        int DRAW_MINE = localBoard.getDRAW_MINE();
        int MARKED_MINE_CELL = localBoard.getMARKED_MINE_CELL();
        int DRAW_MARK = localBoard.getDRAW_MARK();
        int DRAW_WRONG_MARK = localBoard.getDRAW_WRONG_MARK();
        int DRAW_COVER = localBoard.getDRAW_COVER();

        try {
            field = localBoard.getField();
        } catch (RemoteException ex) {
            Logger.getLogger(BoardJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                cell = field[(i * cols) + j];

                if (inGame && cell == MINE_CELL) {
                    inGame = false;
                }

                //Paint mines corresponding to the images
                if (!inGame) {
                    if (cell == COVERED_MINE_CELL) {
                        cell = DRAW_MINE;
                    } else if (cell == MARKED_MINE_CELL) {
                        cell = DRAW_MARK;
                    } else if (cell > COVERED_MINE_CELL) {
                        cell = DRAW_WRONG_MARK;
                    } else if (cell > MINE_CELL) {
                        cell = DRAW_COVER;
                    }

                } else {
                    if (cell > COVERED_MINE_CELL) {
                        cell = DRAW_MARK;
                    } else if (cell > MINE_CELL) {
                        cell = DRAW_COVER;
                        uncover++;
                    }
                }
                g.drawImage(localBoard.getImg()[cell], (j * localBoard.getCELL_SIZE()), (i * localBoard.getCELL_SIZE()), this);
            }
        }

        boolean solved = localBoard.isSolved();
        JLabel statusbar = localBoard.getStatusbar();

        if (uncover == 0 && inGame && !solved) {
            inGame = false;
            statusbar.setText("Game won");
        } else if (uncover == -1 && !inGame && solved) {
            statusbar.setText("Solved");
        } else if (!inGame) {
            statusbar.setText("Game lost");
        }
    }

    public void processClick(int x, int y, int button) throws RemoteException {
        localBoard.processClick(x, y, button);
        boolean rep = localBoard.isRep();
        if (rep) {
            repaint();
            localBoard.pushFieldToUndoStack();
        }
    }

//Click event when user clicked a field

class MinesAdapter extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        try {
            processClick(e.getX(), e.getY(), e.getButton());
        } catch (RemoteException ex) {
            Logger.getLogger(BoardJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}

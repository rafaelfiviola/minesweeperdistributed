package mineSweeper.jogo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mineSweeper.udp.ServidorUdp;

public class MineFrame {

    //time the game was paused for
    private static double pauseTime = 0.0;
    private static double startPauseTime = 0;

    //Declare GUI objects
    private static JFrame frame;
    private static JPanel gamePanel;
    private static JLabel statusbar;
    private static JLabel nomes;

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

    private static boolean host;
    private static String remoteIP, localIP;
    
    private static String nick;
    
    //Declare the menu bar and its items (GUI elements)
    private static String getService(String serverIp, int port) {
        return "rmi://" + serverIp + ":" + port + "/RemoteGameService";
    }
    public MineFrame(boolean host, String remoteIP) throws NotBoundException, MalformedURLException, RemoteException {
            new MineFrame(host, remoteIP, null);
    }
    public MineFrame(boolean host, String remoteIP, String name) throws NotBoundException, MalformedURLException, RemoteException {

        frame = new JFrame();//Create the frame for the GUI
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Have the application exit when closed
        frame.setTitle("Minesweeper");//Title of the frame
        frame.setResizable(false);//Have the frame re-sizable useful for custom games
        if (!(name.isEmpty())){
                nick = name;
            }else{
                nick = "Anônimo";
            }
        statusbar = new JLabel("");//Set the passed-in status bar
        nomes = new JLabel("");
        gamePanel = new JPanel(new BorderLayout());//New panel that contains the board
        try {
            localIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(MineFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        frame.add(gamePanel);//Add gamePanel to the frame
        this.host = host;
        this.remoteIP = remoteIP;

        //o servidor udp é iniciado para permitir o descobrimento do ip do servidor pelos clientes
        if (host) {
            System.out.println("Iniciando Servidor UDP");
            ServidorUdp su = new ServidorUdp();
            su.go(name);
        }

        startNewGame();
        frame.setVisible(true);//Show all components on the window
    }

    //Method to start/restart the game when a game has been lost, restarted or loaded
    public static void startNewGame() throws NotBoundException, MalformedURLException, RemoteException {
        gamePanel.removeAll();
        undoStack.removeAllElements();
        redoStack.removeAllElements();
        gamePanel.add(statusbar, BorderLayout.SOUTH);
        gamePanel.add(nomes, BorderLayout.NORTH);
        BoardJpanel board = new BoardJpanel(statusbar, noOfMines, noOfRows, noOfCols, host, remoteIP);
        board.setNome(nick);
        Board teste = null;
        try {
            teste = new BoardImpl(board);
        } catch (RemoteException ex) {
            Logger.getLogger(MineFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Logger.getLogger(MineFrame.class.getName()).log(Level.INFO, "Launching Server");

        Thread server = new Thread(new LaunchServer(7879, localIP, teste));
        server.start();

        gamePanel.add(board, BorderLayout.CENTER);

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
        if (host) {
           
                Esperando e = new Esperando();
                e.setVisible(true);
                System.out.println("Board Conect:" + board.isConect());

                while (!(board.isConect())) { //enquanto não tem um cliente espera alguem conectar 
                    System.out.println("");
                }

                System.out.println("Board Conect:" + board.isConect());
                e.dispose();

                //seta aqui o remoteIP com o ip que o cliente passou ao serviço quando chamou a função informa do host
                remoteIP = board.getRemoteIP();
            

            try {
                board.setRemoteBoard((Board) Naming.lookup(getService(remoteIP, 7879)));
            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(MineFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                ((Board) (Naming.lookup(getService(remoteIP, 7879)))).informa(localIP);
            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(MineFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        nick = "Jogadores: " + nick + " e " + ((Board) (Naming.lookup(getService(remoteIP, 7879)))).getNome();
        nomes.setText(nick);
    }

    //Accessors and mutators
    //Accessor for the number of mines
    public static int getNoOfMines() {
        return noOfMines;
    }

    public static JLabel getStatusbar() {
        return statusbar;
    }

    public static boolean isHost() {
        return host;
    }

    public static String getRemoteIP() {
        return remoteIP;
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

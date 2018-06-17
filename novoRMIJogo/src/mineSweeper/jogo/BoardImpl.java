/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.jogo;

import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar
 */
public class BoardImpl extends java.rmi.server.UnicastRemoteObject implements Board {

    //Ip do cliente que é passado ao host quando ele chama a função informa
    BoardJpanel game;

    public BoardImpl() throws RemoteException {
        super();
    }

    public BoardImpl(BoardJpanel game) throws RemoteException {
        super();
        this.game = game;
    }

    @Override
    public int[] getField() throws RemoteException {
        return game.getField();
    }

    @Override
    public void processClick(int x, int y, int button, boolean remoteCall) throws RemoteException {
        try {
            game.processClick(x, y, button, remoteCall);
        } catch (NotBoundException ex) {
            Logger.getLogger(BoardImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BoardImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void informa(String clientIP) throws RemoteException {
        System.out.println("informou");
        game.setRemoteIP(clientIP);
        System.out.println("passou clientIP");
        game.setConect(true);
        System.out.println("Setou Conect");
    }

    @Override
    public void newGame() throws java.rmi.RemoteException {
        game.newGame();
    }

}

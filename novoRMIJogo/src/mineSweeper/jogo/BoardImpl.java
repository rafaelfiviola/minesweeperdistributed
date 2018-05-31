/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.jogo;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

/**
 *
 * @author Cesar
 */
public class BoardImpl extends java.rmi.server.UnicastRemoteObject implements Board{

    BoardJpanel game;
            
    public BoardImpl() throws RemoteException{
        super();
    }
    
    public BoardImpl(BoardJpanel game) throws RemoteException{
        super();
        this.game = game;
    }

    
    @Override
    public int[] getField() throws RemoteException {
        return game.getField();
    }

    @Override
    public void processClick(int x, int y, int button, boolean remoteCall) throws RemoteException {
        game.processClick(x, y, button,remoteCall);
    }
    
    @Override
    public void informa(){
       game.setConect(true);
   }
}

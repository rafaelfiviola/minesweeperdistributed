/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.jogo;

/**
 *
 * @author Cesar
 */
public interface Board extends java.rmi.Remote  {
    
    public int[] getField() throws java.rmi.RemoteException;
    
    public void processClick(int x, int y, int button, boolean remoteCall) throws java.rmi.RemoteException;
    
    public void informa();
}

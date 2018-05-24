/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.rmi;

/**
 *
 * @author Cesar
 */
public interface Server extends java.rmi.Remote {

    public int requestBoard(Object Board) throws java.rmi.RemoteException;

    //TODO Retornar Board
    public int SendClick(int x, int y, boolean button) throws java.rmi.RemoteException;

}

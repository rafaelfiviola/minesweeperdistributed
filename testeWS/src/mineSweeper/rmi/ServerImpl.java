/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.rmi;

import java.rmi.RemoteException;

/**
 *
 * @author Cesar
 */
public class ServerImpl extends java.rmi.server.UnicastRemoteObject implements Server {

    public ServerImpl() throws RemoteException {
        super();
    }

    @Override
    public int SendClick(int x, int y, boolean button) throws RemoteException {
        //TODO passar o click para ser processado pela aplicação
        return 1;
    }

    @Override
    public int requestBoard(Object Board) throws RemoteException {
        return 1;
    }
    
    public void teste(){}

}

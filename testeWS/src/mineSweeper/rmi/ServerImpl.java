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

    private int count =0;
    public ServerImpl() throws RemoteException {
        super();
    }

    @Override
    public int SendClick(int x, int y, int button) throws RemoteException {
        //TODO passar o click para ser processado pela aplicação
        count++;
        return 1;
    }

    @Override
    public int requestBoard(Object Board) throws RemoteException {      
        return count;
    }
    
    public void teste(){}

}

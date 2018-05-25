/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.jogo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import mineSweeper.rmi.Server;
import mineSweeper.rmi.ServerImpl;
import mineSweeper.rmi.ServerLaunch;

/**
 *
 * @author Cesar
 */
public class LaunchServer implements Runnable{
    private int port;
    private String serverIp;
    Registry registry = null;
    Board board;

    public LaunchServer(int port, String serverIp,Board board) {
        this.port = port;
        this.serverIp = serverIp;
        this.board = board;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Override
    public void run() {
        System.setProperty("java.rmi.server.hostname", serverIp);
        try {
            registry = LocateRegistry.createRegistry(port);
            Naming.rebind("rmi://"+serverIp+":"+port+"/RemoteGameService", board);
       
        } catch (RemoteException ex) {
            Logger.getLogger(ServerLaunch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerLaunch.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

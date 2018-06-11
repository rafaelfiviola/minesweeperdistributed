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

/**
 *
 * @author Cesar
 */
public class LaunchServer implements Runnable {

    private int port;
    private String serverIp;
    Registry registry;
    Board board;

    public LaunchServer(Integer port, String serverIp, Board board) {
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
        // System.setProperty("java.rmi.server.hostname", serverIp);
        try {
            registry = LocateRegistry.createRegistry(port);
            Logger.getLogger(LaunchServer.class.getName()).log(Level.INFO, "Running launchServer");
            Logger.getLogger(LaunchServer.class.getName()).log(Level.INFO, "rmi://" + serverIp + ":" + port + "/RemoteGameService", board);
            
            Naming.rebind("rmi://" + serverIp + ":" + port + "/RemoteGameService", board);
            
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(LaunchServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.rmi;

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
public class ServerLaunch implements Runnable {

    private int port;
    private String serverIp;
    Registry registry = null;

    public ServerLaunch(int port, String serverIp) {
        this.port = port;
        this.serverIp = serverIp;
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
            Server server = new ServerImpl();
            Naming.rebind("rmi://"+serverIp+":"+port+"/RemoteGameService", server);
       
        } catch (RemoteException ex) {
            Logger.getLogger(ServerLaunch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerLaunch.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

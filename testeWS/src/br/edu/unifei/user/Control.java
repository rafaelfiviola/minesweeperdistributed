/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.user;

import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import mineSweeper.rmi.Server;

/**
 *
 * @author Cesar
 */
public class Control implements Runnable {

    private boolean host;
    private String localIP;
    private String remoteIP;
    private int port;

    public Control(boolean host, String localIP, String remoteIP, int port) {
        this.host = host;
        this.localIP = localIP;
        this.remoteIP = remoteIP;
        this.port = port;
    }

    public Control(boolean host) {
        this.host = host;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public String getLocalIP() {
        return localIP;
    }

    public void setLocalIP(String localIP) {
        this.localIP = localIP;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String getService(String serverIp) {
        return "rmi://" + serverIp + ":" + port + "/RemoteGameService";
    }

    @Override
    public void run() {
        while (true) {
            try {

                Server localServ = (Server) Naming.lookup(getService(localIP));
                localServ.SendClick(port, port, host);
                System.out.println("Resp:" + localServ.requestBoard(null));

            } catch (NotBoundException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

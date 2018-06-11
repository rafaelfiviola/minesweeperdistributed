/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mineSweeper.jogo.ListarServidores;
import mineSweeper.jogo.ServerInfo;

/**
 *
 * @author laser
 */
public class ClienteUdp implements Runnable {

    private ListarServidores ls;
    private DatagramSocket socketUdp;
    private DatagramPacket packetUdp;
    private DatagramPacket packetReceivedUdp;
    private int serverPort;
    private static final int BUFFERSIZE = 1024;
    private static final int MAXTRIES = 1;
    private byte[] buffer;
    private byte[] buffer2;
    private static ArrayList<ServerInfo> serverList;
    private boolean notFinish = true;

    @Override
    public void run() {
        fetchServerList();
    }

    public ClienteUdp(ListarServidores ls) {
        this.ls = ls;
    }

    public void fetchServerList() {
        serverPort = 50000;
        int tries = 0;
        //boolean receivedResponse = false;      
        serverList = new ArrayList<>();
        try {
            InetAddress broadcast = InetAddress.getByName("192.168.0.255");
            // 255.255.255.255
            socketUdp = new DatagramSocket();
            socketUdp.setBroadcast(true);
            buffer = new byte[BUFFERSIZE];
            buffer = (new String("123")).getBytes();
            buffer2 = new byte[BUFFERSIZE];
            packetUdp = new DatagramPacket(buffer, buffer.length, broadcast, serverPort);
            packetReceivedUdp = new DatagramPacket(buffer2, buffer2.length, broadcast, serverPort);
            //O loop é executado dependendo do valor da quantidade de MAXTRIES. Cada pacote recebido (contendo o ip do servidor) é adicionado na lista de servers
            socketUdp.send(packetUdp);
            do {
                try {
                    socketUdp.receive(packetReceivedUdp);

                    if (packetReceivedUdp != null) {
                        ByteArrayInputStream bais = new ByteArrayInputStream(buffer2);
                        BufferedInputStream bis = new BufferedInputStream(bais);
                        ObjectInputStream is = new ObjectInputStream(bis);
                        this.serverList.add((ServerInfo) is.readObject());
                    }
                    ls.updateServCbx(serverList);
                } catch (InterruptedIOException e) {
                    tries += 1;
                    System.out.println("Timed out, " + (MAXTRIES - tries) + " more tries ...");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClienteUdp.class.getName()).log(Level.SEVERE, null, ex);
                }
                tries += 1;
            } while ((tries < MAXTRIES));
            socketUdp.close();

        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(ClienteUdp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteUdp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<ServerInfo> getServerList() {
        return serverList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.udp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laser
 */
public class ServidorUdp implements Runnable {
    private static ServidorUdp servidorUdp;
    private DatagramSocket socketUdp;
    private DatagramPacket packetUdp;
    private int serverPort;
    private static final int BUFFERSIZE = 1024;
    private static final int MAXTRIES = 5;
    private byte[] buffer;
    
    
    public static void go(){
        Thread threadServidorUdp = new Thread(servidorUdp = new ServidorUdp());
        threadServidorUdp.start();
    }
    
    
    @Override
    public void run() {
        
        serverPort = 50000;
        try {
            socketUdp = new DatagramSocket(serverPort);
            buffer = new byte[BUFFERSIZE];
            packetUdp = new DatagramPacket(buffer,buffer.length);
            //O loop espera uma requisição e a responde passando o ip do servidor
            for(;;){
                socketUdp.receive(packetUdp);
                Logger.getLogger(ServidorUdp.class.getName()).log(Level.INFO,"Handling client at " + packetUdp.getAddress().getHostAddress() + " on port " + packetUdp.getPort());
                buffer = (new String(InetAddress.getLocalHost().getHostAddress())).getBytes();
                packetUdp.setData(buffer); 
                socketUdp.send(packetUdp);
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServidorUdp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServidorUdp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

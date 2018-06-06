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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laser
 */
public class ClienteUdp implements Runnable {
    private static ClienteUdp clienteUdp;
    private DatagramSocket socketUdp;
    private DatagramPacket packetUdp;
    private DatagramPacket packetReceivedUdp;
    private int serverPort;
    private static final int BUFFERSIZE = 1024;
    private static final int MAXTRIES = 5;
    private byte[] buffer;
    private byte[] buffer2;
    private static ArrayList<String> serverList = new ArrayList<>();
    private boolean notFinish = true;
    
    public static void go(){
        Thread threadClienteUdp = new Thread(clienteUdp = new ClienteUdp());
        threadClienteUdp.start();
    }

    public static ArrayList<String> getServerList() {
        return clienteUdp.serverList;
    }
    
    public static boolean getNotFinish() {
        return clienteUdp.notFinish;
    }
    
    @Override
    public void run() {
        
        serverPort = 50000;
        int tries = 0;
        //boolean receivedResponse = false;      
        
        try {
            InetAddress broadCast = InetAddress.getByName("200.235.71.255");
            // 255.255.255.255
            socketUdp = new DatagramSocket();
            socketUdp.setBroadcast(true);
            buffer = new byte[BUFFERSIZE];
            buffer = (new String("123")).getBytes();
            buffer2 = new byte[BUFFERSIZE];
            packetUdp = new DatagramPacket(buffer, buffer.length,broadCast,serverPort); 
            packetReceivedUdp = new DatagramPacket(buffer2, buffer2.length,broadCast,serverPort);
            //O loop é executado dependendo do valor da quantidade de MAXTRIES. Cada pacote recebido (contendo o ip do servidor) é adicionado na lista de servers
            socketUdp.send(packetUdp);
            do{
                try{
                    socketUdp.receive(packetReceivedUdp);
                    //O primeiro packet udp recebido é nulo, por isso verifica
                    if(packetReceivedUdp != null){
                        clienteUdp.serverList.add(new String(packetReceivedUdp.getData()));
                        clienteUdp.notFinish = false; 
                    }
                    //receivedResponse = true;
                }catch (InterruptedIOException e){ 
                    tries += 1;
                    System.out.println("Timed out, " + (MAXTRIES - tries) + " more tries ...");
                } 
                tries += 1;
            }while((tries < MAXTRIES));
            socketUdp.close();            
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(ClienteUdp.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(ClienteUdp.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mineSweeper.jogo.ServerInfo;

/**
 *
 * @author laser
 */
public class ServidorUdp implements Runnable {

    private DatagramSocket socketUdp;
    private DatagramPacket packetUdp;
    private int serverPort;
    private static final int BUFFERSIZE = 1024;
    private static final int MAXTRIES = 5;
    private byte[] buffer;

    public static void go() {
        Thread threadServidorUdp = new Thread(new ServidorUdp());
        threadServidorUdp.start();
    }

    @Override
    public void run() {

        serverPort = 50000;
        try {
            socketUdp = new DatagramSocket(serverPort);
            buffer = new byte[BUFFERSIZE];
            ServerInfo si = new ServerInfo();

            //O loop espera uma requisição e a responde passando o ip do servidor
            for (;;) {
                System.out.println("testing");
                receiveRequest();
                sendServerInfo(si);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorUdp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void receiveRequest() throws IOException {
        packetUdp = new DatagramPacket(buffer, buffer.length);
        socketUdp.receive(packetUdp);
        Logger.getLogger(ServidorUdp.class.getName()).log(Level.INFO, "Handling client at " + packetUdp.getAddress().getHostAddress() + " on port " + packetUdp.getPort());
    }

    public void sendServerInfo(ServerInfo si) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(5000);
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.flush();
        os.writeObject(si);
        os.flush();
        buffer = baos.toByteArray();
        packetUdp.setData(buffer);
        socketUdp.send(packetUdp);

    }
}

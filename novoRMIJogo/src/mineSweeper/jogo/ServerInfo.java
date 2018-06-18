/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.jogo;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Fischer Viola <rafaelfiviola@gmail.com>
 */
public class ServerInfo implements Serializable {

    private String name;
    private String difficulty;
    private Integer numberOfPlayers;
    private String ipAddressString;
    private InetAddress ipAddress;
    private Integer port;

    /*
    Esse construtor deve receber a variável que guarda as infos do servidor e criar um modelo para ser exibido na JComboBox
     */
    public ServerInfo() {
        try {
            this.ipAddress = InetAddress.getLocalHost();
            this.ipAddressString = InetAddress.getLocalHost().getHostAddress();
            this.port = 50000;
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerInfo(ServerInfo s) {
        this.name = s.name;
        this.difficulty = s.difficulty;
        this.numberOfPlayers = s.numberOfPlayers;
        this.ipAddressString = s.ipAddressString;
    }

    public String getIpAndPort() {
        return ipAddressString.concat(":").concat(port.toString());
    }

    @Override
    public String toString() {
        return name + " - " + ipAddressString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty == null ? "N/A" : difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getNumberOfPlayers() {
        
        return numberOfPlayers == null ? 1 : numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getIpAddressString() {
        return ipAddressString;
    }

    public void setIpAddressString(String ipAddressString) {
        this.ipAddressString = ipAddressString;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

}

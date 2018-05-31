/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.jogo;

/**
 *
 * @author Rafael Fischer Viola <rafaelfiviola@gmail.com>
 */
public class ServerInfo {

    private String name;
    private String difficulty;
    private Integer numberOfPlayers;
    private String ipAdress;
    private Integer port;

    /*
    Esse construtor deve receber a variável que guarda as infos do servidor e criar um modelo para ser exibido na JComboBox
     */
    public ServerInfo() {

    }

    public ServerInfo(ServerInfo s) {
        this.name = s.name;
        this.difficulty = s.difficulty;
        this.numberOfPlayers = s.numberOfPlayers;
        this.ipAdress = s.ipAdress;
    }

    public String getIpAndPort() {
        return ipAdress.concat(":").concat(port.toString());
    }

    @Override
    public String toString() {
        return name.concat(" - ").concat(ipAdress);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}

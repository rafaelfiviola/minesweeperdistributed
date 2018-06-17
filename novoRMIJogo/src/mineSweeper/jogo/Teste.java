/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper.jogo;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van's Sexy Machine
 */
public class Teste {

    public static void main(String[] args) {
        try {
            new MineFrame(true, null); //inicia como servidor
//            new MineFrame(false, "192.168.0.179"); //inicia como cliente
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

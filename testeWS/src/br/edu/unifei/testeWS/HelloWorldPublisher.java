/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.testeWS;

import br.edu.unifei.testeServerInt.RunServer;
import br.edu.unifei.user.Control;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Cesar
 */

public class HelloWorldPublisher {
    public static void main(String[] args) {
        Thread runServ = new Thread(new RunServer());
        runServ.start();
        for (int i = 0; i < 100000; i++);
        Thread client = new Thread(new Control(true));
        client.start();
    }
}

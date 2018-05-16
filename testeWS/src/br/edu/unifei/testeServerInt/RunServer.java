/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.testeServerInt;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Cesar
 */
public class RunServer implements Runnable{

    @Override
    public void run() {
        Endpoint.publish("http://localhost:7879/ws/MineServer", new ServiceServer());
    }
    
}

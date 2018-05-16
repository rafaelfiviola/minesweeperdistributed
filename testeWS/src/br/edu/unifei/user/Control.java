/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.user;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Cesar
 */
public class Control implements Runnable {

    private boolean host;

    public Control(boolean host) {
        this.host = host;
    }

    @Override
    public void run() {
        URL localurl = null;
        try {
            localurl = new URL("http://localhost:7879/ws/MineServer?wsdl");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        QName localqname = new QName("http://testeServerInt.unifei.edu.br/", "ServiceServerService");
        Service localservice = Service.create(localurl, localqname);
        ServiceServer localService = localservice.getPort(ServiceServer.class);

        if (host) {
            System.out.println("resp:" + localService.processClick(0, 0));
        } else {
            System.out.println("deu RUINS");
        }
    }

}

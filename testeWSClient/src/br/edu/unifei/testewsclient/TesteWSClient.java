/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.testewsclient;


import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Cesar
 */
public class TesteWSClient {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {        // TODO code application logic here

//        br.edu.unifei.testeWSClient.HelloWorldImpl port = service.getHelloWorldImplPort();
//        port.getHelloWorldAsString("Pau");
        URL url = new URL("http://localhost:7779/ws/hello?wsdl");
        QName qname = new QName("http://testeWS.unifei.edu.br/", "HelloWorldImplService");
        Service service = Service.create(url,qname);
        HelloWorldImpl test = service.getPort(HelloWorldImpl.class);
        System.out.println("resp:"+ test.getHelloWorldAsString("pau"));
    }

}

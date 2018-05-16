/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.testeWS;

import javax.jws.WebMethod;
import javax.jws.WebService;  
//Service Implementation  
@WebService()  
public class HelloWorldImpl{  
    
    @WebMethod
    public String getHelloWorldAsString(String name) {  
        return "Hello World JAX-WS " + name;  
    }  
}  
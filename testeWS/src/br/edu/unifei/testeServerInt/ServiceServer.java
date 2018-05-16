/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.testeServerInt;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Cesar
 */
@WebService()
public class ServiceServer {
    
    @WebMethod
    public int processClick(int x, int y){
        //TODO chamar as funções que processam os cliques e altera a board atual
        return 1;
    }
    
    @WebMethod
    public int syncBoard(int[] board){
       //TODO aqui eu sobreescrevo o vetor que representa a board do cliente
       //TODO coloca 
       return 1;
    }
    
}

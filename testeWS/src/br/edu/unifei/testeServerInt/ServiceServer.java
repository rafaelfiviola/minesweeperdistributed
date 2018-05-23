/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.testeServerInt;

import javax.jws.WebMethod;
import javax.jws.WebService;
import br.edu.unifei.user.minesweeper.Board;

/**
 *
 * @author Cesar
 */
@WebService()
public class ServiceServer {
    Board campo;
    int [] field;
    
    @WebMethod
    public int processClick(int x, int y, boolean button){
        //TODO chamar as funções que processam os cliques e altera a board atual
        /*  if(botao pressionado == botão direito) 
                button = true;
            else
                button = false;
        */
        field = campo.mousePressed(x, y, button);
        
        return 1;
    }
    
    @WebMethod
    public int syncBoard(int[] board){
       //TODO aqui eu sobreescrevo o vetor que representa a board do cliente
       //TODO coloca 
        System.out.println("sync board");
       return 1;
    }
    
}

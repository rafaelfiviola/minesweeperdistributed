/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.user;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cesar
 */
public class Board implements Runnable{
    public static List<int[]> filaBoard = new ArrayList<>();
    
    @Override
    public void run() {
        if(!filaBoard.isEmpty()){
            //TODO Desenhar a Board;
        }
    }
    
    
}

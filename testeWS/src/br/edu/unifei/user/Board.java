/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.user;

/**
 *
 * @author Cesar
 */
public interface Board extends java.rmi.Remote  {
    
    public int[] getField() throws java.rmi.RemoteException;
    
    public void processClick(int x, int y, int button) throws java.rmi.RemoteException;
    
    
}

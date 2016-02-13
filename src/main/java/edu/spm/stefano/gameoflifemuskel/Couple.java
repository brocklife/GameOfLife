/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spm.stefano.gameoflifemuskel;

/**
 *
 * @author stefano
 */
public class Couple {
    int a;
    int b;
    
    public Couple(int a, int b){
        this.a = a;
        this.b = b;
    }
    
    public int getA(){
        return a;
    }
    
    public int getB(){
        return b;
    }
    
    @Override
    public String toString(){
        return "("+a+", "+b+")";
    }
}

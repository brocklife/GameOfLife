/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spm.stefano.gameoflife;

/**
 *
 * @author stefano
 */
public class Interval {
    public int a;
    public int b;
    
    public Interval(int a, int b){
        this.a = a;
        this.b = b;
    }
    
    @Override
    public String toString(){
        return "("+a+", "+b+")";
    }
}

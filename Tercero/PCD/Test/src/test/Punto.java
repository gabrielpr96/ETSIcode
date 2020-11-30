/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


public class Punto implements Comparable<Punto> {

    private int idem = 0;
    private float x = 0;
    private float y = 0;

    public Punto() {
        this.idem = 0;
        this.x = 0;
        this.y = 0;
    }

    public Punto(int idem, float x, float y) {
        this.idem = idem;
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public float getIdem() {
        return this.idem;
    }

    public String toString() {
        return ("Id: " + this.idem + "   (" + this.x + "," + this.y+")");
    }

    @Override
    public int compareTo(Punto o) {
        return x<o.x?-1:1;
    }

}

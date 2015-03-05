/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.model;

/**
 * @author gabriel
 *
 * Código baseado no original disponibilizado em:
 * https://github.com/utluiz/java-playground/blob/master/random-code/src/main/java/br/com/starcode/angulovetores/AnguloVetores.java
 *
 */
public class Vector {

    private static final Point pzero = new Point(0, 0);

    private final Point p;
    private final Point q;

    private Vector(Point p, Point q) {
        this.p = p;
        this.q = q;
    }

    public static Double getAngle(Integer x1, Integer y1, Integer x2, Integer y2) {
        Vector p = new Vector(pzero, new Point(x1, y1));
        Vector q = new Vector(pzero, new Point(x2, y2));

        return Math.acos(
                (p.i() * q.i() + p.j() * q.j()) / (p.size() * q.size())
        );
    }

    private double i() {
        return q.getX() - p.getX();
    }

    private double j() {
        return q.getY() - p.getY();
    }

    public double size() {
        return Math.sqrt(
                (q.getX() - p.getX()) * (q.getX() - p.getX())
                + (q.getY() - p.getY()) * (q.getY() - p.getY()));
    }
}

class Point {

    Integer x, y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}

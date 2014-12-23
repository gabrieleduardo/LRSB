/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.xmlElements;

/**
 *
 * @author gabriel
 */
public class TextFix extends Fix {

    private Integer block;
    private String text;
    private Integer x;
    private Integer y;
    private Integer dur;

    /**
     * Construtor Vazio
     */
    public TextFix() {
    }

    /**
     * Construtor da classe TextFix.
     * @param time
     * @param tt
     * @param win
     * @param cursor
     * @param block
     * @param text
     * @param x
     * @param y
     * @param dur
     */
    public TextFix(Integer time, Integer tt, Integer win, Integer cursor, Integer block, String text, Integer x, Integer y, Integer dur) {
        super(time, tt, win,cursor);
        this.cursor = cursor;
        this.block = block;
        this.text = text;
        this.x = x;
        this.y = y;
        this.dur = dur;
    }

    /**
     * Método get da variável block
     * @return the block
     */
    public Integer getBlock() {
        return block;
    }

    /**
     * Método set da variável block
     * @param block the block to set
     */
    public void setBlock(Integer block) {
        this.block = block;
    }

    /**
     * Método get da variável text
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Método set da variável text
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Método get da variável x
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     * Método set da variável x
     * @param x the x to set
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Método get da variável y
     * @return the y
     */
    public Integer getY() {
        return y;
    }

    /**
     * Método set da variável y
     * @param y the y to set
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * Método get da variável dur
     * @return the dur
     */
    public Integer getDur() {
        return dur;
    }

    /**
     * Método set da variável dur
     * @param dur the dur to set
     */
    public void setDur(Integer dur) {
        this.dur = dur;
    }

}

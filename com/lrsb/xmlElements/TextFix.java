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

    public TextFix() {
        this(0, 0, 0, 0, 0, "", 0, 0, 0);
    }

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
     * @return the block
     */
    public Integer getBlock() {
        return block;
    }

    /**
     * @param block the block to set
     */
    public void setBlock(Integer block) {
        this.block = block;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public Integer getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * @return the dur
     */
    public Integer getDur() {
        return dur;
    }

    /**
     * @param dur the dur to set
     */
    public void setDur(Integer dur) {
        this.dur = dur;
    }

}

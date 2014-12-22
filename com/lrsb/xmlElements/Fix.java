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
public class Fix {

    protected Integer time;
    protected Integer tt;
    protected Integer win;
    protected Integer cursor;
    
    public Fix(){
        this(0,0,0,0);
    }

    public Fix(Integer time, Integer tt, Integer win,Integer cursor) {
        this.time = time;
        this.tt = tt;
        this.win = win;
        this.cursor = cursor;
    }

    /**
     * @return the time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * @return the tt
     */
    public Integer getTt() {
        return tt;
    }

    /**
     * @param tt the tt to set
     */
    public void setTt(Integer tt) {
        this.tt = tt;
    }

    /**
     * @return the win
     */
    public Integer getWin() {
        return win;
    }

    /**
     * @param win the win to set
     */
    public void setWin(Integer win) {
        this.win = win;
    }

    /**
     * @return the cursor
     */
    public Integer getCursor() {
        return cursor;
    }

    /**
     * @param cursor the cursor to set
     */
    public void setCursor(Integer cursor) {
        this.cursor = cursor;
    }
    
    
    
}

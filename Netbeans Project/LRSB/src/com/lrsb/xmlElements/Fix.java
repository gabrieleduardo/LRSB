/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.xmlElements;

/**
 * Classe responsável por armazenar as fixações.
 * @author gabriel
 */
public class Fix implements XmlEvent{

    /**
     * Tempo da fixação
     */
    protected Integer time;

    /**
     * Tempo total da fixação
     */
    protected Integer tt;

    /**
     * Janela da Fixação
     */
    protected Integer win;

    /**
     * Cursor
     */
    protected Integer cursor;
    
    /**
     * Construtor vazio
     */
    public Fix(){
    }

    /**
     * Construtor da classe fix.
     * @param time - Tempo
     * @param tt - Tempo Total
     * @param win - Janela
     * @param cursor - Cursor
     */
    public Fix(Integer time, Integer tt, Integer win,Integer cursor) {
        this.time = time;
        this.tt = tt;
        this.win = win;
        this.cursor = cursor;
    }

    /**
     * Método get da variável time
     * @return the time
     */
    @Override
    public Integer getTime() {
        return time;
    }

    /**
     * Método set da variável time
     * @param time the time to set
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * Método get da variável tt
     * @return the tt
     */
    public Integer getTt() {
        return tt;
    }

    /**
     * Método set da variável tt
     * @param tt the tt to set
     */
    public void setTt(Integer tt) {
        this.tt = tt;
    }

    /**
     * Método get da variável Win
     * @return the win
     */
    public Integer getWin() {
        return win;
    }

    /**
     * Método set da variável Win
     * @param win the win to set
     */
    public void setWin(Integer win) {
        this.win = win;
    }

    /**
     * Método get da variável cursor
     * @return the cursor
     */
    public Integer getCursor() {
        return cursor;
    }

    /**
     * Método get da variável cursor
     * @param cursor the cursor to set
     */
    public void setCursor(Integer cursor) {
        this.cursor = cursor;
    }
    
    
    
}

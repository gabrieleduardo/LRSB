/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.xmlElements;

/**
 * Classe responsável por armazenar ações. As ações de Mouse são o padrão,
 * aç~pes de teclado são armazenadas na classe filha Key.
 * @author gabriel
 */
public class Action implements XmlEvent{

    /**
     * Tempo que a ação foi realizada.
     */
    protected Integer time;

    /**
     * Cursor
     */
    protected Integer cursor;

    /**
     * Valor da Ação
     */
    protected String value;

    /**
     * Tipo da ação: Mouse ou Key.
     */
    protected String action;

    /**
     *  Construtor da classe Action.
     * 
     * @param time
     * @param cursor
     * @param value
     * @param action
     */
    public Action(Integer time, Integer cursor, String value, String action) {
        this.time = time;
        this.cursor = cursor;
        this.value = value;
        this.action = action;
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
     * Método get da variável cursor
     * @return the cursor
     */
    public Integer getCursor() {
        return cursor;
    }

    /**
     * Método set da variável cursor
     * @param cursor the cursor to set
     */
    public void setCursor(Integer cursor) {
        this.cursor = cursor;
    }

    /**
     * Método get da variável value
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Método set da variável value
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Método get da variável action
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * Método set da variável action
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }
}

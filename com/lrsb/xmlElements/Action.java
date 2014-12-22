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
public class Action {
    
    /**
     * Note: Simple Action object represent a Mouse Action.
     */

    protected Integer time;
    protected Integer cursor;
    protected String value;
    protected String action;

    public Action(Integer time, Integer cursor, String value, String action) {
        this.time = time;
        this.cursor = cursor;
        this.value = value;
        this.action = action;
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

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }
}

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
public class Key extends Action {
    
    protected String type;
    protected Integer Width;
    protected Integer Height;

    public Key(Integer time, Integer cursor, String value, String action,String type, Integer Width, Integer Height) {
        super(time, cursor, value, action);
        this.type = type;
        this.Width = Width;
        this.Height = Height;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the Width
     */
    public Integer getWidth() {
        return Width;
    }

    /**
     * @param Width the Width to set
     */
    public void setWidth(Integer Width) {
        this.Width = Width;
    }

    /**
     * @return the Height
     */
    public Integer getHeight() {
        return Height;
    }

    /**
     * @param Height the Height to set
     */
    public void setHeight(Integer Height) {
        this.Height = Height;
    }
    
    
}

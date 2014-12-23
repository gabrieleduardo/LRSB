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
    
    /**
     * Tipo de ação de tecla
     */
    private String type;

    /**
     * Largura
     */
    private Integer Width;

    /**
     * Altura
     */
    private Integer Height;

    /**
     * Construtor da clase Key.
     * Herda da classe Action.
     * 
     * @param time - Tempo
     * @param cursor - Cursor
     * @param value - Valor
     * @param action - Ação
     * @param type - Tipo
     * @param Width - Largura
     * @param Height - Altura
     */
    public Key(Integer time, Integer cursor, String value, String action,String type, Integer Width, Integer Height) {
        super(time, cursor, value, action);
        this.type = type;
        this.Width = Width;
        this.Height = Height;
    }

    /**
     * Método get da variável Type
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Método set da variável type
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Método get da variável Width
     * @return the Width
     */
    public Integer getWidth() {
        return Width;
    }

    /**
     * Método set da variável Width
     * @param Width the Width to set
     */
    public void setWidth(Integer Width) {
        this.Width = Width;
    }

    /**
     * Método get da variável Height
     * @return the Height
     */
    public Integer getHeight() {
        return Height;
    }

    /**
     * Método set da variável Height
     * @param Height the Height to set
     */
    public void setHeight(Integer Height) {
        this.Height = Height;
    }
    
    
}

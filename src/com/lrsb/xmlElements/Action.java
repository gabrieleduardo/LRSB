/**
 * LRSB - Linear Representation Spreedsheat Builder - Pauses Analysis of XML files generated by Translog II software.
 * For Translog II details, see http://bridge.cbs.dk/platform/?q=Translog-II.
 *
 * Developed with a grant from the Federal University of Uberlândia, Brazil (Project 2014PBG000883, Supervisor:
 * Prof. Dr. Igor A. Lourenço da Silva)
 *
 * Copyright (C) 2015 Gabriel Ed. da Silva
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see
 * http://www.gnu.org/licenses/.
 */
package com.lrsb.xmlElements;

/**
 * Classe responsável por armazenar ações. As ações de Mouse são o padrão, ações
 * de teclado são armazenadas na classe filha Key.
 *
 * @author gabriel
 */
public class Action implements XmlEvent {

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
     * Construtor da classe Action.
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
     *
     * @return the time
     */
    @Override
    public Integer getTime() {
        return time;
    }

    /**
     * Método set da variável time
     *
     * @param time the time to set
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * Método get da variável cursor
     *
     * @return the cursor
     */
    public Integer getCursor() {
        return cursor;
    }

    /**
     * Método set da variável cursor
     *
     * @param cursor the cursor to set
     */
    public void setCursor(Integer cursor) {
        this.cursor = cursor;
    }

    /**
     * Método get da variável value
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Método set da variável value
     *
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Método get da variável action
     *
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * Método set da variável action
     *
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Método toString sobreescrito para retornar o valor da ação e não
     * informações sobre o objeto.
     *
     * @return O valor da ação em formato texto.
     */
    @Override
    public String toString() {        
        return "{" + value + "}";
    }
}

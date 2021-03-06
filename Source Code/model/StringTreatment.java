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

package com.lrsb.model;

/**
 * Classe responsável pelo tratamento de Strings
 *
 * @author gabriel
 */
public class StringTreatment {

    /**
     * Requisitos Funcionais: RF07
     *
     * A classe recebe o texto fonte e retorna as duas primeiras palavras do
     * mesmo, afim de satisfazer o RF07.
     *
     * @param st - Texto Fonte
     * @return Uma string com as duas primeiras palavras do texto fonte.
     */
    public static String getFirstTwo(String st) {
        String[] strs = st.split(" ");
        String returnValue = strs[0];

        if (strs.length > 1) {
            returnValue = returnValue + strs[1];
        }

        return returnValue;

    }

    /**
     * Requisitos Funcionais: RF07
     *
     * A classe recebe o texto fonte e retorna os dois primeiros caracteres do
     * mesmo, afim de satisfazer o RF07.
     *
     * @param st - Texto Fonte
     * @return Uma string com as duas primeiras palavras do texto fonte.
     */
    public static String getFirstTwoAsian(String st) {
        String returnValue = "";
        char[] charArray = st.toCharArray();

        if (charArray.length > 2) {
            returnValue = returnValue + charArray[0] + charArray[1];
        } else {
            returnValue = st;
        }

        return returnValue;

    }

    /**
     * Retorna o nome do arquivo XML através de seu caminho.
     *
     * @param path - Caminho do documento
     * @return Nome do Documento.
     */
    public static String getXMLName(String path) {
        String[] strs;

        if (System.getProperty("os.name").startsWith("Windows")) {
            strs = path.split("\\\\"); //Necessita de um escape
        } else {
            strs = path.split("/");
        }

        String returnValue = strs[strs.length - 1];
        returnValue = returnValue.replace(".xml", "");
        return returnValue;
    }

    /**
     * Corrige um erro percebido no campo Languages de um documento, onde as
     * aspas não era fechadas corretamente.
     *
     * @param st - String do campo Languages.
     * @return String corrigida.
     */
    public static String replaceLanguage(String st) {
        return st.replace("”", "\"");
    }

    /**
     * Formata um número com duas casas decimais.
     *
     * @param n Um número
     * @return O número com duas casas decimais.
     */
    public static String format2f(Number n) {
        String st = String.format("%.2f", n);
        return st.replaceAll(",", ".");
    }
    
    /**
     * Realiza a troca de espaços por underline "_".
     * 
     * @param st Palavra
     * @return Palavra com espaços trocados por underline
     */
    
    public static String replaceSpaces(String st){
        return st.replaceAll(" ","_");
    }

}

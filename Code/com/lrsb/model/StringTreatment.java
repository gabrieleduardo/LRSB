/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.model;

/**
 * Classe responsável pelo tratamento de Strings
 * @author gabriel
 */
public class StringTreatment {

    /**
     * REquisitos Funcionais: RF07
     * 
     * A classe recebe o texto fonte e retorna as duas primeiras palavras 
     * do mesmo, afim de satisfazer o RF07..
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
     * Retorna a barra adequada para o sistema operacional. 
     * 
     * Sua utilização é necessária para processar corretamente os caminho do
     * Windows. O mesmo utiliza o backslash (Barra para trás) para separar seus 
     * diretórios sendo que a mesma barra é utiliza como caractere de escape em
     * váriaslinguagens de programação. Por sua vez, o Linux e o MacOS utilizam
     * a fowardslash (barra para frente) para separação de seus diretórios.
     *
     * @return A barra adequada para o sistema operacional utilizado.
     */
    public static String getSlash() {
        //Microsoft Windows
        if (System.getProperty("os.name").startsWith("Windows")) {
            return "\\";
        }
        //Linux and MacOs
        return "/";
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
    public static String replaceLanguage(String st){
        return st.replace("”","\"");
    }

}

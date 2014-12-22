/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.model;

/**
 *
 * @author gabriel
 */
public class StringTreatment {

    /**
     * RF07
     *
     * @param st
     * @return
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
     * Gets the proper slash to the operating system
     *
     * @return Proper Slash to the SO.
     */
    public static String getSlash() {
        //Microsoft Windows
        if (System.getProperty("os.name").startsWith("Windows")) {
            return "\\";
        }
        //Linux and MacOs
        return "/";
    }

    public static String getXMLName(String path) {
        String[] strs;

        if (System.getProperty("os.name").startsWith("Windows")) {
            strs = path.split("\\\\"); //Needs an escape
        } else {
            strs = path.split("/");
        }

        String returnValue = strs[strs.length - 1];
        returnValue = returnValue.replace(".xml", "");
        return returnValue;
    }
    
    public static String replaceLanguage(String st){
        return st.replace("”","\"");
    }

}

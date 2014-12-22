/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.config;

import static com.lrsb.model.StringTreatment.getSlash;
import java.io.Serializable;

/**
 * RF02, RF03, RF08, RF09
 * 
 * @author gabriel
 */
public class Config implements Serializable{
    
    public boolean fullPackage;
    public Integer defaultPauseBegin;
    public Integer defaultPauseEnd;
    public String defaultPath;
    
    private Config() {
        fullPackage = false;
        defaultPath = getSlash()+"Spreadsheets"; //Adicionar o diretório padrão.
        defaultPauseBegin = 2400;
        defaultPauseEnd = 2400;
    }
    
    public static Config getInstance() {
        return ConfigHolder.INSTANCE;
    }
    
    private static class ConfigHolder {

        private static final Config INSTANCE = new Config();
    }
}

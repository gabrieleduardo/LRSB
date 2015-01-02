/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.config;

import java.io.File;
import java.io.Serializable;

/**
 * Requisitos Funcionais: RF02, RF03, RF08, RF09
 * Descrição de classe: A Classe Config é responsável por salvar as configurações do usuário.
 * 
 * @author Gabriel Ed.
 */
public class Config implements Serializable{
    
    /**
     * Váriavel responsável por marcar se devem ser processados documentos individuais
     * ou diretórios completos.
     */
    
    public boolean fullDirectory;

    /**
     * Valor de pausa inicial do intervalo
     */
    public String defaultPauseBegin;

    /**
     * Valor de pausa final do intervalo
     */
    public String defaultPauseEnd;

    /**
     * Caminho padrão para os documentos gerados
     */
    public String defaultPath;
    
    public String userDir = System.getProperty("user.home")+File.separator+"LRSB"+File.separator+"Spreadsheets";
    
    private Config() {
        fullDirectory = false;
        defaultPath = userDir; //Adicionar o diretório padrão.
        defaultPauseBegin = "2400";
        defaultPauseEnd = "*";
        makeDir();
    }
    
    public void resetConfig(){
        makeDir();
        saveConfig(false,userDir,"2400","*");
    }
    
    public void makeDir(){
        new File(userDir).mkdirs();
    }
    
    
    
    public void saveConfig(Boolean fullDirectory,String defaultPath,String defaultPauseBegin,String defaultPauseEnd){
        this.fullDirectory = fullDirectory;
        this.defaultPath = defaultPath;
        this.defaultPauseBegin = defaultPauseBegin;
        this.defaultPauseEnd = defaultPauseEnd;
        boolean save = ConfigPersistence.save(this);
    }

    public boolean isFullDirectory() {
        return fullDirectory;
    }

    public String getDefaultPauseBegin() {
        return defaultPauseBegin;
    }

    public String getDefaultPauseEnd() {
        return defaultPauseEnd;
    }

    public String getDefaultPath() {
        return defaultPath;
    }
    
    /**
     * Recupera uma instância única da classe Config.
     * @return Instância da classe Config
     */
    public static Config getInstance() {
        return ConfigHolder.INSTANCE;
    }
    
    private static class ConfigHolder {
        private static final Config INSTANCE = configInstanceInit();
        
        private static Config configInstanceInit(){
            Config cfg = (Config)ConfigPersistence.load();
            if(cfg == null){
                cfg = new Config();
                ConfigPersistence.save(cfg);
            }
            return cfg;
        }
    }
}

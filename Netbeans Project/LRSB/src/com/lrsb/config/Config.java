/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.config;

import static com.lrsb.model.StringTreatment.getSlash;
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
    
    public boolean fullPackage;

    /**
     * Valor de pausa inicial do intervalo
     */
    public Integer defaultPauseBegin;

    /**
     * Valor de pausa final do intervalo
     */
    public Integer defaultPauseEnd;

    /**
     * Caminho padrão para os documentos gerados
     */
    public String defaultPath;
    
    private Config() {
        fullPackage = false;
        defaultPath = getSlash()+"Spreadsheets"; //Adicionar o diretório padrão.
        defaultPauseBegin = 2400;
        defaultPauseEnd = 2400;
    }
    
    /**
     * Recupera uma instância única da classe Config.
     * @return Instância da classe Config
     */
    public static Config getInstance() {
        return ConfigHolder.INSTANCE;
    }
    
    private static class ConfigHolder {
        private static final Config INSTANCE = new Config();
    }
}

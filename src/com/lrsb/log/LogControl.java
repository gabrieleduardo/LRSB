/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe responsável pelo tratamento e persistência dos logs da aplicação.
 * @author gabze
 */
public class LogControl {
    
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    
    /**
     * Escreve uma mensagem  no log.
     * @param logMessage Mensagem a ser escrita no log. 
     */
    public static void log(String logMessage){
        writeLog(formatLog(logMessage));
    } 
    
    /**
     * Formata a mensagem para o formato: "[dd/MM/YYYY HH:mm:ss] Mensagem".
     * @param logMessage Mensagem a ser escrita no log.
     * @return Mensagem formatada com informações de data e hora.
     */
    private static String formatLog(String logMessage){        
        return "["+DATE_FORMAT.format(new Date())+"] "+logMessage+"\n";
    }
    
    /**
     * Escreve a mensagem no log.
     * TODO: Alterar a mensagem para ser persistida em texto.
     * @param logMessage Mensagem a ser escrita.
     */
    private static void writeLog(String logMessage){
        System.out.println(logMessage);
    }            
            
}

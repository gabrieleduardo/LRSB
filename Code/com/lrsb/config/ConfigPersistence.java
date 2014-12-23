/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável pela persistência da configuração
 * @author gabriel
 */
public class ConfigPersistence {

    private static final String path = "/Config/cfg";

    /**
     * Salva a configuração como um objeto serializavel.
     * @param obj - Objeto da classe Config.
     * @return Valor booleano representando o sucesso ou fracasso da operação
     */
    public static boolean save(Object obj) {

        FileOutputStream fileWriter = null;
        ObjectOutputStream objWriter;

        try {
            fileWriter = new FileOutputStream(path);
            objWriter = new ObjectOutputStream(fileWriter);
            objWriter.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigPersistence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigPersistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return true;

    }

    /**
     * Classe responsável por carregar um objeto de Configuração.
     * @return O objeto de Configuração
     */
    public static Object load() {
        ObjectInputStream objReader;
        FileInputStream fileReader = null;
        Object obj = null;
        try {
            fileReader = new FileInputStream(path);
            objReader = new ObjectInputStream(fileReader);
            obj = objReader.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return obj;
    }
}

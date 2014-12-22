/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.view;

import com.lrsb.model.StringTreatment;
import com.lrsb.xmlElements.XmlDocument;
import com.lrsb.xmlElements.XmlReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gabriel
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        String st = "C:"+StringTreatment.getSlash()+"XML"+StringTreatment.getSlash()+"P10_T2.xml";
        try {
            XmlDocument xd = XmlReader.parseDocument(st);
            System.out.println(xd.getSubject());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

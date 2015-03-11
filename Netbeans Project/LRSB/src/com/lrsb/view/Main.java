/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.view;

import com.lrsb.model.Document;
import com.lrsb.model.Processor;
import com.lrsb.model.Segment;
import com.lrsb.spreadsheet.SaveToCSV;
import com.lrsb.xmlElements.XmlDocument;
import com.lrsb.xmlElements.XmlReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author gabriel
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stages st = Stages.getInstance();
        st.showStage();
        
        //XmlDocument xdoc = XmlReader.parseDocument("C:\\XML\\P10_T2.xml");
        //Document doc = Processor.doRender(xdoc);
        //SaveToCSV.save(doc,"teste.csv");
        //System.exit(0);   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

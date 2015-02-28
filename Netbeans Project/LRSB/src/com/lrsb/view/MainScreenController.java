/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.view;

import com.lrsb.config.Config;
import com.lrsb.model.Document;
import com.lrsb.model.Processor;
import com.lrsb.spreadsheet.SaveToCSV;
import com.lrsb.xmlElements.XmlDocument;
import com.lrsb.xmlElements.XmlReader;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author gabriel
 */
public class MainScreenController implements Initializable {

    @FXML
    private TextField resourcePathTF;
    @FXML
    private TextField targetPathTF;

    @FXML
    private TextField pauseBegin;

    @FXML
    private TextField pauseEnd;
    
    @FXML
    private ToggleButton fullDirectory;
    
    @FXML
    private Button start;
    
    @FXML
    private Button reset;

    @FXML
    private void resourcePathSearch(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Resource Directory");
        File file = dc.showDialog(new Stage());
        resourcePathTF.setText(file.toString());
    }

    @FXML
    private void targetPathSearch(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Target Directory");
        File file = dc.showDialog(new Stage());
        targetPathTF.setText(file.toString());
    }
    
    @FXML
    public void setValues(){
        Config cfg = Config.getInstance();
        this.fullDirectory.setSelected(cfg.isFullDirectory());
        this.pauseBegin.setText(cfg.getDefaultPauseBegin());
        this.pauseEnd.setText(cfg.getDefaultPauseEnd());
        this.targetPathTF.setText(cfg.defaultPath);
        this.resourcePathTF.setText("");
    }
    
    @FXML
    public void startProcess(){
        try {
            XmlDocument xdoc = XmlReader.parseDocument("‪C:\\XML\\P10_T2.xml");
            Document doc = Processor.doRender(xdoc);
            SaveToCSV.save(doc,targetPathTF.getText()+".csv");
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void reset(ActionEvent event){
        setValues();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setValues();
    }

}

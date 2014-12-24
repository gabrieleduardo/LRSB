/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author gabriel
 */
public class FXMLDocumentController implements Initializable {

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.view;

import com.sun.javafx.application.HostServicesDelegate;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

/**
 *
 * @author gabriel
 */
public class MenuBarController implements Initializable {

    @FXML
    private MenuItem preferences;
    
    @FXML
    private MenuItem tutorial;
    
    @FXML
    private MenuItem about;
    
    @FXML
    private MenuItem quit;

    @FXML
    private void openMenu(){
        Stages.getInstance().showMenu();
    }
    
    @FXML
    private void quit(){
        System.exit(0);
    }
    
    @FXML
    private void openTutorial(){
        Stages.getInstance().showTutorialStage();
    }
    
    @FXML
    private void openAbout(){
        try {
            java.awt.Desktop.getDesktop().browse(new URI("http://globo.com"));
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(MenuBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

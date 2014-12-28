/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.event.HyperlinkEvent;

/**
 *
 * @author gabriel
 */
public class Stages {

    private final Stage menuStage;
    private final Stage mainStage;
    private VBox mainScreen;
    private AnchorPane menuScreen;

    private Stages() {
        this.mainStage = new Stage();
        this.menuStage = new Stage();
        initMainStage();
        initMenuStage();
    }

    private void initMainStage() {
        try {
            this.mainStage.setResizable(false);
            this.mainStage.setTitle("Linear Representation Spreadsheet Builder");

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
            mainScreen = (VBox) loader.load();
            Scene scene = new Scene(mainScreen);
            mainStage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Stages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initMenuStage() {
        try {
            this.menuStage.setResizable(false);
            this.menuStage.setTitle("Linear Representation Spreadsheet Builder");

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("MenuScreen.fxml"));
            menuScreen = (AnchorPane) loader.load();
            Scene scene = new Scene(menuScreen);
            menuStage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Stages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMainStage(){
        this.mainStage.show();
    }
    
    public void showMenuStage(){
        this.menuStage.show();
    }
    
    public void hideMainStage(){
        this.mainStage.hide();
    }
    
    public void hideMenuStage(){
        this.menuStage.hide();
    }

    public static Stages getInstance() {
        return StagesHolder.INSTANCE;
    }

    private static class StagesHolder {

        private static final Stages INSTANCE = new Stages();
    }
}

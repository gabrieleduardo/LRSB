/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author gabriel
 */
public class Stages {

    private final String tutorial = "http://gabrieleduardo.github.io/MicroUnits";
    
    private final Stage stage;
    private final Stage browserStage;
    private Scene mainScene;
    private Scene menuScene;
    private Scene tutorialScene;
    private BorderPane primaryScreen;
    private BorderPane primaryScreen2;
    private MenuBar menuBar;
    private MenuBar menuBar2;
    private AnchorPane mainScreen;
    private AnchorPane menuScreen;

    private Stages() {
        this.stage = new Stage();
        this.browserStage = new Stage();
        initAll();
    }

    private void initAll() {
        try {
            // Inicia as telas
            this.stage.setResizable(false);
            this.stage.setTitle("Linear Representation Spreadsheet Builder");
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("PrimaryScreen.fxml"));
            primaryScreen = (BorderPane) loader.load();
            loader = new FXMLLoader(Main.class.getResource("PrimaryScreen.fxml"));
            primaryScreen2 = (BorderPane) loader.load();

            // Inicia a barra de menu
            loader = new FXMLLoader(Main.class.getResource("MenuBar.fxml"));
            menuBar = (MenuBar) loader.load();
            loader = new FXMLLoader(Main.class.getResource("MenuBar.fxml"));
            menuBar2 = (MenuBar) loader.load();
            // Inicia a tela principal
            loader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
            mainScreen = (AnchorPane) loader.load();
            // Inicia a tela de menu
            loader = new FXMLLoader(Main.class.getResource("MenuScreen.fxml"));
            menuScreen = (AnchorPane) loader.load();

            // Configura a tela principal
            primaryScreen.setTop(menuBar);
            primaryScreen.setCenter(mainScreen);
            mainScene = new Scene(primaryScreen);
            mainScene.setRoot(primaryScreen);
            // Configura a tela de menu
            primaryScreen2.setTop(menuBar2);
            primaryScreen2.setCenter(menuScreen);
            menuScene = new Scene(primaryScreen2);
            menuScene.setRoot(primaryScreen2);

            // Inicia o Browser
            
            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            webEngine.load(tutorial);
            tutorialScene = new Scene(browser);

            showMain();
        } catch (IOException ex) {
            Logger.getLogger(Stages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void showTutorialStage(){
        browserStage.setScene(tutorialScene);
        this.browserStage.show();
    }

    public void showStage() {
        this.stage.show();
    }

    public void showMain() {
        stage.setScene(mainScene);
    }

    public void showMenu() {
        stage.setScene(menuScene);
    }

    public static Stages getInstance() {
        return Stages2Holder.INSTANCE;
    }

    private static class Stages2Holder {

        private static final Stages INSTANCE = new Stages();
    }
}

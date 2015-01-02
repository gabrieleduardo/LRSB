/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.view;

import com.lrsb.config.Config;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
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
public class MenuScreenController implements Initializable {

    @FXML
    private TextField defaultDirectoryPath;

    @FXML
    private TextField defaultPauseBegin;

    @FXML
    private TextField defaultPauseEnd;

    @FXML
    private ToggleButton fullDirectory;

    @FXML
    private Button searchBt;

    @FXML
    private Button saveBt;

    @FXML
    private Button cancelBt;

    @FXML
    private Button resetBt;

    @FXML
    private void defaultDirectoryPathSearch(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Resource Directory");
        File file = dc.showDialog(new Stage());
        defaultDirectoryPath.setText(file.toString());
    }

    @FXML
    private void cancelButtonEvent(ActionEvent event) {
        this.setValues();
        Stages.getInstance().showMain();
    }

    @FXML
    private void saveButtonEvent(ActionEvent event) {
        Config.getInstance().saveConfig(this.fullDirectory.isSelected(),defaultDirectoryPath.getText(),
                defaultPauseBegin.getText(), defaultPauseEnd.getText());
        this.setValues();
        Stages.getInstance().showMain();
    }

    @FXML
    private void resetButtonEvent(ActionEvent event) {
        Config.getInstance().resetConfig();
        this.setValues();
        Stages.getInstance().showMain();
    }

    @FXML
    private void setValues() {
        Config cfg = Config.getInstance();
        this.defaultDirectoryPath.setText(cfg.getDefaultPath());
        this.defaultPauseBegin.setText(cfg.defaultPauseBegin);
        this.defaultPauseEnd.setText(cfg.defaultPauseEnd);
        this.fullDirectory.setSelected(cfg.fullDirectory);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setValues();
    }

}

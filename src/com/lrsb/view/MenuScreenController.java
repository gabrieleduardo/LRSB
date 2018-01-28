/**
 * LRSB - Linear Representation Spreedsheat Builder - Pauses Analysis of XML files generated by Translog II software. 
 * For Translog II details, see http://bridge.cbs.dk/platform/?q=Translog-II.
 * 
 * Developed with a grant from the Federal University of Uberlândia, Brazil (Project 2014PBG000883, Supervisor: 
 * Prof. Dr. Igor A. Lourenço da Silva)
 * 
 * Copyright (C) 2015 Gabriel Ed. da Silva
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General 
 * Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see
* http://www.gnu.org/licenses/.
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
        defaultDirectoryPath.setText("D:\\CSV\\");
        /*
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Resource Directory");
        File file = dc.showDialog(new Stage());
        defaultDirectoryPath.setText(file.toString());
        */
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

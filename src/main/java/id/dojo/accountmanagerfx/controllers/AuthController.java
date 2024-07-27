package id.dojo.accountmanagerfx.controllers;

import com.sun.tools.javac.Main;
import id.dojo.accountmanagerfx.MainApp;
import id.dojo.accountmanagerfx.helpers.Saver;
import id.dojo.accountmanagerfx.models.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField passwordField;
    @FXML
    private Button submidButton;
    private MainApp mainApp;
    private Config config;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        config = Saver.retrieveConfig();
        if (config.getPassword() == null || config.getPassword().isEmpty()){
            passwordLabel.setText("Masukkan Password baru");
        }

        submidButton.setOnAction(event -> {
            if (handleSubmit())
                mainApp.showHomePage();
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public boolean handleSubmit(){
        if (passwordField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
            return false;
        }

        if (!config.getPassword().isEmpty()){
            if (!(config.getPassword().equalsIgnoreCase(passwordField.getText())))
                return false;
        }else {
            config.setPassword(passwordField.getText());
            Saver.saveConfig(config);
        }

        return true;
    }

}

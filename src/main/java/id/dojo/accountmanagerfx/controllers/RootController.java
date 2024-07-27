package id.dojo.accountmanagerfx.controllers;

import id.dojo.accountmanagerfx.helpers.Saver;
import id.dojo.accountmanagerfx.models.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    private Stage rootStage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleConfiguration() throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory =
                directoryChooser.showDialog(rootStage);

        if(selectedDirectory == null){
            System.out.println("No Directory selected");
        }else{
            System.out.println(selectedDirectory.getAbsolutePath());

            String accountFilePath = selectedDirectory.getAbsolutePath() + "/account.txt";
            String historyFilePath = selectedDirectory.getAbsolutePath() + "/pass_history.txt";
            String configFilePath = selectedDirectory.getAbsolutePath() + "/config.txt";

            File accountFile = new File(accountFilePath);
            File historyFile = new File(historyFilePath);
            File configFile = new File(configFilePath);

            if (accountFile.createNewFile()) System.out.println("berhasil");
            if (historyFile.createNewFile()) System.out.println("berhasil");
            if (configFile.createNewFile()) System.out.println("berhasil");

            Saver.fileName = accountFilePath;
            Saver.fileHistory = historyFilePath;
            Saver.fileConfig = configFilePath;

            Config config = new Config(accountFilePath, historyFilePath, configFilePath);
            Saver.saveConfig(config);
        }
    }
}

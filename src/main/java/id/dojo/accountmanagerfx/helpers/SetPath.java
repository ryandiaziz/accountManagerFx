package id.dojo.accountmanagerfx.helpers;

import id.dojo.accountmanagerfx.models.Config;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SetPath {
    private static Stage configPathStage;
    public static void configPath() throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory =
                directoryChooser.showDialog(configPathStage);

        if(selectedDirectory == null){
            System.out.println("No Directory selected");
        }else{
            System.out.println(selectedDirectory.getAbsolutePath());

            String accountFilePath = selectedDirectory.getAbsolutePath() + "/account.txt";
            String historyFilePath = selectedDirectory.getAbsolutePath() + "/pass_history.txt";

            File theDir = new File(System.getProperty("user.home")+"/account_manager/config");
            if (!theDir.exists()){
                theDir.mkdirs();
            }
            String configFilePath = System.getProperty("user.home")+"/account_manager/config/config.txt";

            Saver.fileName = accountFilePath;
            Saver.fileHistory = historyFilePath;
            Saver.fileConfig = configFilePath;

            File accountFile = new File(accountFilePath);
            File historyFile = new File(historyFilePath);
            File configFile = new File(configFilePath);

            if (accountFile.createNewFile()) System.out.println("berhasil");
            if (historyFile.createNewFile()) System.out.println("berhasil");
            if (configFile.createNewFile()) System.out.println("berhasil");

            Config config = new Config(accountFilePath, historyFilePath, configFilePath);
            Saver.saveConfig(config);
        }
    }
}

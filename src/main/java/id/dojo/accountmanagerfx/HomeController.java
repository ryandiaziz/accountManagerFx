package id.dojo.accountmanagerfx;

import com.sun.tools.javac.Main;
import id.dojo.accountmanagerfx.helpers.Saver;
import id.dojo.accountmanagerfx.models.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private TableView<Account> tableViewAccount;
    @FXML
    private TableColumn<Account, String> tableName;
    @FXML
    private TableColumn<Account, String> tableUsername;
    @FXML
    private TableColumn<Account, String> tablePassword;

    private MainApp mainApp;

    private ObservableList<Account> accountData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableUsername.setCellValueFactory(cellData -> cellData.getValue().getAccountNameProperty());
        tableName.setCellValueFactory(cellData -> cellData.getValue().getAccountNameProperty());
        tablePassword.setCellValueFactory(cellData -> cellData.getValue().getAccountPasswordProperty());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        tableViewAccount.setItems(mainApp.getAccountData());
    }
}

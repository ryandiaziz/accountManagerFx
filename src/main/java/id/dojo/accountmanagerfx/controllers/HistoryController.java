package id.dojo.accountmanagerfx.controllers;

import id.dojo.accountmanagerfx.MainApp;
import id.dojo.accountmanagerfx.models.PassHistory;
import id.dojo.accountmanagerfx.models.PassHistoryDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private TableView<PassHistory> tableViewHistory;
    @FXML
    private TableColumn<PassHistory, String> columnChangedAt;
    @FXML
    private TableColumn<PassHistory, String> columnAccountName;
    @FXML
    private TableColumn<PassHistory, String> columnOldPassword;
    @FXML
    private TableColumn<PassHistory, String> columnNewPassword;
    private List<PassHistoryDto> passHistoryDtoList;
    private MainApp mainApp;
    private Stage historyStage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnChangedAt.setCellValueFactory(cellData -> cellData.getValue().getChangedAtProperty());
        columnAccountName.setCellValueFactory(cellData -> cellData.getValue().getAccountNameProperty());
        columnOldPassword.setCellValueFactory(cellData -> cellData.getValue().getOldPasswordProperty());
        columnNewPassword.setCellValueFactory(cellDdata -> cellDdata.getValue().getNewPasswordProperty());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.passHistoryDtoList = mainApp.getPassHistoryDtoList();

        tableViewHistory.setItems(mainApp.getHistoryData());
    }

    public void setHistoryStage(Stage historyStage) {
        this.historyStage = historyStage;
    }

    @FXML
    private void handleBack() {
        historyStage.close();
    }
}

package id.dojo.accountmanagerfx;

import com.sun.tools.javac.Main;
import id.dojo.accountmanagerfx.helpers.Saver;
import id.dojo.accountmanagerfx.models.Account;
import id.dojo.accountmanagerfx.models.AccountDto;
import id.dojo.accountmanagerfx.models.PassHistory;
import id.dojo.accountmanagerfx.models.PassHistoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    @FXML
    private TextField accountNameField;
    @FXML
    private TextField accountUsernameField;
    @FXML
    private TextField accountPasswordField;
    private MainApp mainApp;
    private List<AccountDto> accountDtoList;
    private List<PassHistoryDto> passHistoryDtoList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableUsername.setCellValueFactory(cellData -> cellData.getValue().getAccountUsernameProperty());
        tableName.setCellValueFactory(cellData -> cellData.getValue().getAccountNameProperty());
//        tablePassword.setCellValueFactory(cellData -> cellData.getValue().getAccountPasswordProperty());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.accountDtoList = mainApp.getAccountDtoList();
        this.passHistoryDtoList = mainApp.getPassHistoryDtoList();

        // Add observable list data to the table
        tableViewAccount.setItems(mainApp.getAccountData());
    }

    @FXML
    public void handleCreateAccount(){
        if (isInputValid()){
            AccountDto accountDto = new AccountDto(accountNameField.getText(), accountUsernameField.getText(), accountPasswordField.getText());
            accountDtoList.add(accountDto);

            Saver.saveObject(accountDtoList);
            mainApp.getAccountData().add(new Account(accountNameField.getText(), accountUsernameField.getText(), accountPasswordField.getText()));
            accountNameField.setText("");
            accountUsernameField.setText("");
            accountPasswordField.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = tableViewAccount.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableViewAccount.getItems().remove(selectedIndex);
            accountDtoList.remove(accountDtoList.get(selectedIndex));
            Saver.saveObject(accountDtoList);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditAccount() {
        Account selectedAccount = tableViewAccount.getSelectionModel().getSelectedItem();
        String currentPass = selectedAccount.getAccount_password();
        int selectedIndex = tableViewAccount.getSelectionModel().getSelectedIndex();
        if (selectedAccount != null) {
            boolean okClicked = mainApp.showEditAccount(selectedAccount);

            if (okClicked) {
                AccountDto accountDto = accountDtoList.get(selectedIndex);
                accountDto.setName(selectedAccount.getAccount_name());
                accountDto.setUserName(selectedAccount.getAccount_username());
                accountDto.setPassword(selectedAccount.getAccount_password());
                Saver.saveObject(accountDtoList);
                if (!currentPass.equalsIgnoreCase(selectedAccount.getAccount_password())){
                    PassHistoryDto passHistoryDto = new PassHistoryDto(selectedAccount.getAccount_name(), currentPass, selectedAccount.getAccount_password());
                    passHistoryDtoList.addFirst(passHistoryDto);
                    Saver.saveHistory(passHistoryDtoList);
                    mainApp.getHistoryData().addFirst(new PassHistory(passHistoryDto.getChangeAt(), passHistoryDto.getAccountName(), passHistoryDto.getOldPass(), passHistoryDto.getNewPass()));
                }
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleShowDetail(){
        Account selectedAccount = tableViewAccount.getSelectionModel().getSelectedItem();
        mainApp.showDetailAccount(selectedAccount);
    }

    @FXML
    private void handleShowHistory(){
        mainApp.showHistory();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (accountNameField.getText() == null || accountNameField.getText().length() == 0) {
            errorMessage += "No valid account name!\n";
        }
        if (accountUsernameField.getText() == null || accountUsernameField.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }
        if (accountPasswordField.getText() == null || accountPasswordField.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}

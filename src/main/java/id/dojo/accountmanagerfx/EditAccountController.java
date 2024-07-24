package id.dojo.accountmanagerfx;

import id.dojo.accountmanagerfx.models.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditAccountController implements Initializable {
    @FXML
    private TextField accountNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private Stage dialogStage;
    private Account account;
    private boolean okClicked = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAccount(Account account) {
        this.account = account;

        accountNameField.setText(account.getAccount_name());
        usernameField.setText(account.getAccount_username());
        passwordField.setText(account.getAccount_password());
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            account.setAccount_name(accountNameField.getText());
            account.setAccount_username(usernameField.getText());
            account.setAccount_password(passwordField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (accountNameField.getText() == null || accountNameField.getText().length() == 0) {
            errorMessage += "No valid account name!\n";
        }
        if (usernameField.getText() == null || accountNameField.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}

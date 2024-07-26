package id.dojo.accountmanagerfx;

import id.dojo.accountmanagerfx.models.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailAccountController implements Initializable {
    @FXML
    private Label accountNameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    private Stage detailStage;
    private Account account;
    private boolean okClicked = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDetailStage(Stage detailStage) {
        this.detailStage = detailStage;
    }

    public void setAccount(Account account) {
        this.account = account;

        accountNameLabel.setText(account.getAccount_name());
        usernameLabel.setText(account.getAccount_username());
        passwordLabel.setText(account.getAccount_password());
    }

    @FXML
    private void handleOk() {
        detailStage.close();
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}

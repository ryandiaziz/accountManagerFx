package id.dojo.accountmanagerfx;

import java.io.IOException;
import java.util.List;

import id.dojo.accountmanagerfx.helpers.Saver;
import id.dojo.accountmanagerfx.models.Account;
import id.dojo.accountmanagerfx.models.AccountDto;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private List<AccountDto> accountDtoList;

    private ObservableList<Account> accountData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Account Manager");

        initRootLayout();

        showHome();
    }

    public MainApp(){
        this.accountDtoList = Saver.retrieveObject();
        for (AccountDto accountDto : accountDtoList){
            accountData.add(new Account(accountDto.getName(), accountDto.getUserName(), accountDto.getPassword()));
        }
    }

    // SETTER & GETTER
    public ObservableList<Account> getAccountData() {
        return accountData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public List<AccountDto> getAccountDtoList() {
        return accountDtoList;
    }
    // SETTER & GETTER

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/root-view.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHome() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/home-view.fxml"));
            AnchorPane homeView = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(homeView);

            // Give the controller access to the main app.
            HomeController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showEditAccount(Account account) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/editAccount-view.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Account");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditAccountController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAccount(account);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showDetailAccount(Account account) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/detailAccount-view.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage detailStage = new Stage();
            detailStage.setTitle("Detail Account");
            detailStage.initModality(Modality.WINDOW_MODAL);
            detailStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            detailStage.setScene(scene);

            // Set the person into the controller.
            DetailAccountController controller = loader.getController();
            controller.setDetailStage(detailStage);
            controller.setAccount(account);

            // Show the dialog and wait until the user closes it
            detailStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
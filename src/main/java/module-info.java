module id.dojo.accountmanagerfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens id.dojo.accountmanagerfx to javafx.fxml;
    exports id.dojo.accountmanagerfx;
}
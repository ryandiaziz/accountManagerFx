package id.dojo.accountmanagerfx.models;

import com.sun.source.tree.UsesTree;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private final StringProperty account_name;
    private final StringProperty account_username;
    private final StringProperty account_password;

    public Account() {
        this(null, null, null);
    }

    public Account(String account_name, String account_username, String account_password){
        this.account_name = new SimpleStringProperty(account_name);
        this.account_username = new SimpleStringProperty(account_username);
        this.account_password = new SimpleStringProperty(account_password);
    }

    public StringProperty getAccountNameProperty() {
        return account_name;
    }

    public StringProperty getAccountUsernameProperty(){
        return account_username;
    }

    public StringProperty getAccountPasswordProperty(){
        return account_password;
    }
}

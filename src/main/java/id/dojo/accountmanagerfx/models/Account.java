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

    public String getAccount_name() {
        return account_name.get();
    }

    public void setAccount_name(String account_name){
        this.account_name.set(account_name);
    }

    public StringProperty getAccountUsernameProperty(){
        return account_username;
    }

    public String getAccount_username() {
        return account_username.get();
    }

    public void setAccount_username(String account_username){
        this.account_username.set(account_username);
    }

    public StringProperty getAccountPasswordProperty(){
        return account_password;
    }

    public String getAccount_password() {
        return account_password.get();
    }

    public void setAccount_password(String account_password){
        this.account_password.set(account_password);
    }
}

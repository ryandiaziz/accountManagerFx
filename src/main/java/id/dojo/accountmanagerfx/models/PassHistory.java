package id.dojo.accountmanagerfx.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PassHistory {
    private final StringProperty changed_at;
    private final StringProperty account_name;
    private final StringProperty old_password;
    private final StringProperty new_password;

    public PassHistory() {
        this(null, null, null,null);
    }

    public PassHistory(String changed_at, String account_name, String old_password, String new_password){
        this.changed_at = new SimpleStringProperty(changed_at);
        this.account_name = new SimpleStringProperty(account_name);
        this.old_password = new SimpleStringProperty(old_password);
        this.new_password = new SimpleStringProperty(new_password);
    }

    public String getChangedAt() {
        return changed_at.get();
    }
    public  StringProperty getChangedAtProperty(){
        return changed_at;
    }
    public void setChangedAt(String changedAt){
        this.changed_at.set(changedAt);
    }

    public String getAccountName() {
        return account_name.get();
    }
    public  StringProperty getAccountNameProperty(){
        return account_name;
    }
    public void setAccountName(String accountName){
        this.account_name.set(accountName);
    }

    public String getOldPassword() {
        return old_password.get();
    }
    public  StringProperty getOldPasswordProperty(){
        return old_password;
    }
    public void setOldPassword(String oldPassword){
        this.old_password.set(oldPassword);
    }

    public String getNewPassword() {
        return new_password.get();
    }
    public  StringProperty getNewPasswordProperty(){
        return new_password;
    }
    public void setNewPassword(String newPassword){
        this.new_password.set(newPassword);
    }
}

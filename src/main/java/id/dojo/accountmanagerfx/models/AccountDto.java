package id.dojo.accountmanagerfx.models;

import java.io.Serializable;

public class AccountDto implements Serializable {
    private String name;
    private String userName;
    private String password;

    public AccountDto(String name, String userName, String password){
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

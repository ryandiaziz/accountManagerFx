package id.dojo.accountmanagerfx.models;

import java.io.Serializable;

public class Config implements Serializable {
    private String accountFile;
    private String historyFile;
    private String configFile;
    private String password;

    public Config(String accountFile, String historyFile, String configFile){
        this.accountFile = accountFile;
        this.historyFile = historyFile;
        this.configFile = historyFile;
        this.password = "";
    }

    public void setAccountFile(String accountFile) {
        this.accountFile = accountFile;
    }

    public String getAccountFile() {
        return accountFile;
    }

    public void setHistoryFile(String historyFile) {
        this.historyFile = historyFile;
    }

    public String getHistoryFile() {
        return historyFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

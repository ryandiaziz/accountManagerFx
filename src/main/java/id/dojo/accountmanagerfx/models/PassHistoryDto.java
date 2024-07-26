package id.dojo.accountmanagerfx.models;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class PassHistoryDto implements Serializable {
    final private String changeAt;
    final private String accountName;
    final private String oldPass;
    final private String newPass;

    public PassHistoryDto(String accountName, String oldPass, String newPass){
        this.accountName = accountName;
        this.changeAt = getDate();
        this.oldPass = oldPass;
        this.newPass = newPass;
    }

    public String getChangeAt() {
        return changeAt;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getOldPass() {
        return oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    private String getDate(){
        Instant sekarang = Instant.now();
        ZonedDateTime zonedDateTime = sekarang.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        String waktuTerformat = zonedDateTime.format(formatter);

        return waktuTerformat;
    }
}
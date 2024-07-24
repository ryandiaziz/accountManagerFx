package id.dojo.accountmanagerfx.helpers;
import id.dojo.accountmanagerfx.models.Account;
import id.dojo.accountmanagerfx.models.AccountDto;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Saver {
    public static String fileName = "/home/ryan/Projects/accountManagerFx/src/main/resources/id/dojo/accountmanagerfx/view/account.txt";

    public static void saveObject(List<AccountDto> accounts){
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOut);

            for (AccountDto account : accounts){
                objectOutputStream.writeObject(account);
            }
//            objectOutputStream.writeObject(account);
            String base64 = Base64.getEncoder().encodeToString(byteOut.toByteArray());

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(base64.getBytes());

            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<AccountDto> retrieveObject(){
        File file = new File(fileName);
        List<AccountDto> accounts = new ArrayList<>();

        if(file.exists() && file.length() > 0){
            try {
                FileInputStream fileIn = new FileInputStream(fileName);
                byte[] base64Bytes = new byte[fileIn.available()];
                fileIn.read(base64Bytes);

                byte[] objectBytes = Base64.getDecoder().decode(new String(base64Bytes));
                ByteArrayInputStream byteIn = new ByteArrayInputStream(objectBytes);
                ObjectInputStream in = new ObjectInputStream(byteIn);

                while (true) {
                    try {
                        AccountDto account = (AccountDto) in.readObject();
                        accounts.add(account);
                    } catch (EOFException e) {
                        break;
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return accounts;
    }

//    public static void saveHistory(List<PassHistory> passHistories){
//        try {
//            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOut);
//
//            for (PassHistory passHistory : passHistories){
//                objectOutputStream.writeObject(passHistory);
//            }
//
//            String base64 = Base64.getEncoder().encodeToString(byteOut.toByteArray());
//
//            FileOutputStream fileOutputStream = new FileOutputStream(fileHistory);
//            fileOutputStream.write(base64.getBytes());
//
//            objectOutputStream.flush();
//            objectOutputStream.close();
//            fileOutputStream.close();
//        }catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static List<PassHistory> retrieveHistory(){
//        File file = new File(fileHistory);
//        List<PassHistory> passHistories = new ArrayList<>();
//
//        if(file.exists() && file.length() > 0){
//            try {
//                FileInputStream fileIn = new FileInputStream(fileHistory);
//
//                byte[] base64Bytes = new byte[fileIn.available()];
//                fileIn.read(base64Bytes);
//
//                byte[] objectBytes = Base64.getDecoder().decode(new String(base64Bytes));
//                ByteArrayInputStream byteIn = new ByteArrayInputStream(objectBytes);
//
//                ObjectInputStream in = new ObjectInputStream(byteIn);
//
//                while (true) {
//                    try {
//                        PassHistory passHistory = (PassHistory) in.readObject();
//                        passHistories.add(passHistory);
//                    } catch (EOFException e) {
//                        break;
//                    }
//                }
//
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return passHistories;
//    }
}


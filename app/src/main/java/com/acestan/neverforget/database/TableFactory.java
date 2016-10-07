package com.acestan.neverforget.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class TableFactory {
    public void createSMS(SQLiteDatabase db) {
        System.out.println("Creating sms table ...");
        String sql = "CREATE TABLE sms (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "content TEXT," +
                "date TEXT," +
                "status VARCHAR (15));";// status tells if the sms is sent or scheduled
        try {
            db.execSQL(sql);
            System.out.println("Successfully created sms table !");
        } catch (Exception e) {
            System.out.println("Failed to create sms table !");
            e.printStackTrace();
        }


    }

    public void createEmail(SQLiteDatabase db) {
        System.out.println("Creating Email table ...");
        String sql = "CREATE TABLE email (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "subject VARCHAR(30)," +
                "body TEXT," +
                "date DATETIME,"+
                "status VARCHAR(15));";
        try {
            db.execSQL(sql);
            System.out.println("Successfuly created email table !");
        } catch (Exception e) {
            System.out.println("Failed to create email table !");
            e.printStackTrace();
        }

    }

    public void createRecipient(SQLiteDatabase db){
        System.out.println("Creating Recipient table ...");
        String sql = "CREATE TABLE recipient (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "mobile VARCHAR(20)," +
                "email VARCHAR(40));";
        try {
            db.execSQL(sql);
            System.out.println("Successfuly created recipient table !");
        } catch (Exception e) {
            System.out.println("Failed to create recipient table !");
            e.printStackTrace();
        }

    }

    public void createSendSMS(SQLiteDatabase db){
        System.out.println("Creating SendSMS table ...");
        String sql =  "CREATE TABLE send_sms (sms_id INTEGER," +
                "recipient_id INTEGER," +
                "FOREIGN KEY(sms_id) REFERENCES sms(id)," +
                "FOREIGN KEY(recipient_id) REFERENCES recipient(id));";
        try {
            db.execSQL(sql);
            System.out.println("Successfuly created SendSMS table !");
        } catch (Exception e) {
            System.out.println("Failed to create SendSMS table !");
            e.printStackTrace();
        }
    }
    public void createSendEmail(SQLiteDatabase db){
        System.out.println("Creating SendEmail table ...");
        String sql =  "CREATE TABLE send_email (email_id INTEGER," +
                "recipient_id INTEGER," +
                "FOREIGN KEY(email_id) REFERENCES email(id)," +
                "FOREIGN KEY(recipient_id) REFERENCES recipient(id));";
        try {
            db.execSQL(sql);
            System.out.println("Successfuly created SendEmail table !");
        } catch (Exception e) {
            System.out.println("Failed to create SendEmail table !");
            e.printStackTrace();
        }
    }
}

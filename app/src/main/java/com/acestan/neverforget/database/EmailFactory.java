package com.acestan.neverforget.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.acestan.neverforget.models.Email;
import com.acestan.neverforget.models.Recipient;
import com.acestan.neverforget.models.SMS;

import java.util.ArrayList;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class EmailFactory {
    public void scheduleEmail(SQLiteDatabase db, ArrayList<Recipient> recipients, Email message, String date) {
        int email_id = Integer.MAX_VALUE;
        ArrayList<Integer> recipient_ids = new ArrayList<Integer>();
        System.out.println("Scheduling e-mail message ...");
        String sql_email = "INSERT INTO email (subject,body,date)" +
                " VALUES('" + message.getSubject() + "', '" + message.getBody() + "', '" + date + "');";
        for (int i = 0; i < recipients.size(); i++) {
            String sql_recipient = "INSERT INTO recipient (mobile,email)" +
                    " VALUES('" + recipients.get(i).getMobile() + "', '" + recipients.get(i).geteMail() + "');";
            try {
                db.execSQL(sql_recipient);
            } catch (Exception e) {

            }
            Cursor c = db.rawQuery("SELECT * FROM SQLITE_SEQUENCE WHERE name = 'recipient'", null);
            if (c.moveToFirst()) {
                recipient_ids.add(c.getInt(1));
            }
        }
        try {
            db.execSQL(sql_email);
            System.out.println("Successfully scheduled e-mail message !");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cursor c = db.rawQuery("SELECT * FROM SQLITE_SEQUENCE WHERE name = 'email'", null);
        if (c.moveToFirst()) {
            email_id = c.getInt(1);
        }
        for (int i = 0; i < recipient_ids.size(); i++) {
            String sql_sendEmail = "INSERT INTO send_email VALUES(" + email_id + ", " + recipient_ids.get(i) + ");";
            try {
                db.execSQL(sql_sendEmail);
                System.out.println("Successfull add into JOIN table for E-Mail and Recipeint(s) !");
            } catch (Exception e) {
                System.out.println("Failed to add into JOIN table for E-Mail and Recipient(s)");
            }
        }


    }
}

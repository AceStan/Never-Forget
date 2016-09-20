package com.acestan.neverforget.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.acestan.neverforget.models.Recipient;
import com.acestan.neverforget.models.SMS;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class SMSFactory implements SMSViewer {
    public void scheduleSMS(SQLiteDatabase db, ArrayList<Recipient> recipients, SMS message, String date) {
        int sms_id = Integer.MAX_VALUE;
        ArrayList<Integer> recipient_ids = new ArrayList<Integer>();
        System.out.println("Scheduling sms message ...");
        String sql_sms = "INSERT INTO sms (title,content,date)" +
                " VALUES('" + message.getTitle() + "', '" + message.getContent() + "', '" + date + "');";
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
            db.execSQL(sql_sms);
            System.out.println("Successfully  scheduled sms message !");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cursor c = db.rawQuery("SELECT * FROM SQLITE_SEQUENCE WHERE name = 'sms'", null);
        if (c.moveToFirst()) {
            sms_id = c.getInt(1);
        }
        for (int i = 0; i < recipient_ids.size(); i++) {
            String sql_sendSms = "INSERT INTO send_sms VALUES(" + sms_id + ", " + recipient_ids.get(i) + ");";
            try {
                db.execSQL(sql_sendSms);
                System.out.println("Successfull add into JOIN table for SMS and Recipeint(s) !");
            } catch (Exception e) {
                System.out.println("Failed to add into JOIN table for SMS and Recipient(s)");
            }
        }


    }

    @Override
    public SMS viewSentSMS(Recipient r) {
        SMS result = new SMS();
        // Join tables using recipient and sms ids

        return null;
    }

    @Override
    public SMS viewSentSMS(Recipient r, Date d) {
        return null;
    }

    @Override
    public SMS viewSentSMS(Date d) {
        return null;
    }
}

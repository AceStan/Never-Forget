package com.acestan.neverforget.database;

import android.Manifest;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.ResourceCursorAdapter;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.acestan.neverforget.R;
import com.acestan.neverforget.models.Recipient;
import com.acestan.neverforget.models.SMS;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class SMSFactory implements SMSViewer {
    public void scheduleSMS(SQLiteDatabase db, ArrayList<Recipient> recipients, SMS message, String date) {
        int sms_id = Integer.MAX_VALUE;
        ArrayList<Integer> recipient_ids = new ArrayList<Integer>();
        System.out.println("Scheduling sms message ...");
        String sql_sms = "INSERT INTO sms (content,date,status)" +
                " VALUES('" + message.getContent() + "', '" + date + "','scheduled');";
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

    public void sendSMS(SQLiteDatabase db, ArrayList<Recipient> recipients, SMS message, String date) {
        final String insertData =   "INSERT INTO sms(content,date,status) VALUES('" +
                message.getContent()+"','"+message.getDate()+"' ,'sent')";
        db.execSQL(insertData);


    }

    public void sendScheduledSMS(SQLiteDatabase db){
        // THINKING ABOUT THE PROCESS !
    }




    @Override
    public ArrayList<SMS> viewScheduledSMS(SQLiteDatabase db, Recipient r) {
        ArrayList<SMS> result = new ArrayList<SMS>();
        ArrayList<Integer> recipient_ids = new ArrayList<Integer>();
        // Join tables using recipient and sms ids
        Cursor c = db.rawQuery("SELECT id FROM recipient WHERE mobile='" + r.getMobile() + "';", null);
        if (c.moveToFirst()) {
            do {
                recipient_ids.add(c.getInt(0));
            } while (c.moveToNext());
        }
        Cursor res;
        for (int i = 0; i < recipient_ids.size(); i++) {
            res = db.rawQuery("SELECT S.* FROM sms AS S,send_sms AS SS WHERE S.id = SS.sms_id AND S.status='scheduled' AND SS.recipient_id='" + recipient_ids.get(i) + "';", null);
            if (res.moveToFirst()) {
                do {
                    result.add(new SMS(res.getString(1), res.getString(2)));
                } while (res.moveToNext());
            }
        }
        return result;
    }

    @Override
    public ArrayList<SMS> viewScheduledSMS(SQLiteDatabase db, Recipient recipient, String date) {
        ArrayList<SMS> result = new ArrayList<SMS>();
        ArrayList<Integer> recipient_ids = new ArrayList<Integer>();
        // Join tables using recipient and sms ids
        Cursor c = db.rawQuery("SELECT id FROM recipient WHERE mobile='" + recipient.getMobile() + "';", null);
        if (c.moveToFirst()) {
            do {
                recipient_ids.add(c.getInt(0));
            } while (c.moveToNext());
        }
        Cursor res;
        for (int i = 0; i < recipient_ids.size(); i++) {
            res = db.rawQuery("SELECT S.* FROM sms AS S,send_sms AS SS WHERE S.id = SS.sms_id AND S.status='scheduled' AND S.date = '" + date + "' AND SS.recipient_id='" + recipient_ids.get(i) + "';", null);
            if (res.moveToFirst()) {
                do {
                    result.add(new SMS(res.getString(1), res.getString(2)));
                } while (res.moveToNext());
            }
        }
        return result;
    }

    @Override
    public ArrayList<SMS> viewScheduledSMS(SQLiteDatabase db, String d) {
        ArrayList<SMS> result = new ArrayList<SMS>();

        Cursor res;

        res = db.rawQuery("SELECT * FROM sms WHERE date = '" + d + "' AND status='scheduled';", null);
        if (res.moveToFirst()) {
            do {
                result.add(new SMS(res.getString(1), res.getString(2)));
            } while (res.moveToNext());
        }

        return result;
    }

    @Override
    public Hashtable<SMS,Recipient> viewSentSMS(SQLiteDatabase db) {
        return null; // NEED TO BE IMPLEMENTED !!!
    }

    @Override
    public Hashtable<SMS, Recipient> viewScheduledSMS(SQLiteDatabase db) {

        Hashtable<SMS,Recipient> result = new Hashtable<SMS,Recipient>();
        Cursor c = db.rawQuery("SELECT S.*,R.* FROM sms AS S,recipient AS R,send_sms AS SS WHERE " +
                "S.id = SS.sms_id AND SS.recipient_id = R.id AND S.status = 'scheduled' ", null);
        if(c.moveToFirst()){
            do{
                for(int i =0;i<c.getColumnCount();i++){
                    SMS s = new SMS();
                    Recipient r = new Recipient();
                    s.set_id(c.getInt(0));
                    s.setContent(c.getString(1));
                    s.setDate(c.getString(2));
                    r.set_id(c.getInt(4));
                    r.setMobile(c.getString(5));
                    r.seteMail(c.getString(6));
                    result.put(s,r);
                }

            }while(c.moveToNext());
        }

        return result;
    }
}


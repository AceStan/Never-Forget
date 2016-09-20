package com.acestan.neverforget.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.acestan.neverforget.models.Recipient;
import com.acestan.neverforget.models.SMS;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, "NFDB", null, 1);
    }

    TableFactory factory = new TableFactory();
    SMSFactory smsFactory = new SMSFactory();
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creating NFBD database ... ");
        factory.createSMS(db);
        factory.createEmail(db);
        factory.createRecipient(db);
        factory.createSendSMS(db);
        factory.createSendEmail(db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Upgrading NFDB database...");
        String sql = "DROP TABLE IF EXISTS sms,email,recipient";
        try {
            db.execSQL(sql);
            System.out.println("Successfully upgraded NFDB database !");

        } catch (Exception e) {
            System.out.println("Failed to upgrade NFBD database !");
            e.printStackTrace();
        }
        onCreate(db);
    }

    public void scheduleSMS(SQLiteDatabase db, ArrayList<Recipient> recipients, SMS message, String date){
        smsFactory.scheduleSMS(db,recipients,message,date);
    }
}

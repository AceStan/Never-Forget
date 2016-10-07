package com.acestan.neverforget.database;

import android.database.sqlite.SQLiteDatabase;

import com.acestan.neverforget.models.Recipient;
import com.acestan.neverforget.models.SMS;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public interface SMSViewer {
    public ArrayList<SMS> viewScheduledSMS(SQLiteDatabase db, Recipient r);
    public ArrayList<SMS> viewScheduledSMS(SQLiteDatabase db,Recipient r, String d);
    public ArrayList<SMS> viewScheduledSMS(SQLiteDatabase db,String d);
    public Hashtable<SMS,Recipient> viewScheduledSMS(SQLiteDatabase db);
    public Hashtable<SMS,Recipient> viewSentSMS(SQLiteDatabase db);
}

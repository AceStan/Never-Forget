package com.acestan.neverforget;

import android.Manifest;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.acestan.neverforget.database.DatabaseHandler;
import com.acestan.neverforget.database.EmailFactory;
import com.acestan.neverforget.database.SMSFactory;
import com.acestan.neverforget.models.Email;
import com.acestan.neverforget.models.Recipient;
import com.acestan.neverforget.models.SMS;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Main activity started !");
        /*DatabaseHandler db = new DatabaseHandler(this);
        SQLiteDatabase database = db.getWritableDatabase();
        SimpleDateFormat SimpleDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String dateToAdd = SimpleDate.format(date);
        Recipient r = new Recipient("070-707-707", "ace@gmail.com");
        Recipient r1 = new Recipient("070-744-707", "ace111@gmail.com");
        ArrayList<Recipient> list = new ArrayList<Recipient>();
        list.add(r);
        list.add(r1);
        SMS sms = new SMS("Greeting", "Helo There", dateToAdd);
        SMS sms1 = new SMS("Greeting user 1", "Helo There user 1", dateToAdd);
        ArrayList<Recipient> list1 = new ArrayList<Recipient>();
        list1.add(r1);
        System.out.println("HERE : ");
        System.out.println(dateToAdd);
        new SMSFactory().scheduleSMS(database, list, sms, dateToAdd);
        new SMSFactory().scheduleSMS(database, list1, sms1, dateToAdd);
        ArrayList<SMS> result = new ArrayList<SMS>();
        ArrayList<Integer> recipient_ids = new ArrayList<Integer>();
        // Join tables using recipient and sms ids
       Cursor c = database.rawQuery("SELECT id FROM recipient WHERE mobile='" + r.getMobile()+"';", null);
        if(c.moveToFirst()){
            do{
                recipient_ids.add(c.getInt(0));
            }while(c.moveToNext());
        }
        for(int i =0;i<recipient_ids.size();i++){
            System.out.println(recipient_ids.get(i));
        }*/
       /*
       SENDING SMS !!!
       ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        try {
            SmsManager mng = SmsManager.getDefault();
            mng.sendTextMessage("078529394", null, "Hello There !", null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }
*/

    }
}

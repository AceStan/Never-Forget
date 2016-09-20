package com.acestan.neverforget;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        DatabaseHandler db = new DatabaseHandler(this);
        SQLiteDatabase database = db.getWritableDatabase();
        SimpleDateFormat SimpleDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String dateToAdd = SimpleDate.format(date);
        Recipient r = new Recipient("070-707-707", "ace@gmail.com");
        Recipient r1 = new Recipient("070-744-707", "ace111@gmail.com");
        ArrayList<Recipient> list = new ArrayList<Recipient>();
        list.add(r);
        list.add(r1);
        Email email = new Email("Greeting", "Helo There",dateToAdd);
        System.out.println("HERE : ");
        System.out.println(dateToAdd);
        new EmailFactory().scheduleEmail(database,list,email,dateToAdd);
        Cursor c = database.rawQuery("SELECT * FROM send_email",null);
        if(c.moveToFirst()){
               do{
                   System.out.println("Message id :"+c.getLong(0)+" Recipient id : "+c.getLong(1));

               }while (c.moveToNext());
        }
        Cursor d = database.rawQuery("SELECT * FROM recipient",null);
        if(d.moveToFirst()){
            do{
                System.out.println("Recipient id in table:"+d.getLong(0));

            }while (d.moveToNext());
        }



    }
}

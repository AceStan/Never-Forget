package com.acestan.neverforget.activities;

import android.Manifest;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.acestan.neverforget.R;
import com.acestan.neverforget.database.DatabaseHandler;
import com.acestan.neverforget.database.SMSFactory;
import com.acestan.neverforget.models.Recipient;
import com.acestan.neverforget.models.SMS;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SendSmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        ImageView send =  (ImageView)findViewById(R.id.sendSmsButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SMSFactory smsFactory = new SMSFactory();
                DatabaseHandler databaseHandler = new DatabaseHandler(SendSmsActivity.this);
                SQLiteDatabase db = databaseHandler.getWritableDatabase();
                EditText to = (EditText)findViewById(R.id.to);
                EditText text = (EditText)findViewById(R.id.sms_content);
                System.out.println(to.getText().toString());
                ActivityCompat.requestPermissions(SendSmsActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
                String tmp =  to.getText().toString();
                tmp = tmp.trim();
                String tmps[] =  tmp.split(" ");
                ArrayList<Recipient> list = new ArrayList<Recipient>();
                for(int i=0;i<tmps.length;i++){
                    list.add(new Recipient(tmps[i],null));
                }
                SimpleDateFormat SimpleDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
                Date date = new Date();
                String dateToAdd = SimpleDate.format(date);
                SMS sms = new SMS(text.getText().toString(),dateToAdd);
                try {
                    smsFactory.sendSMS(db, list, sms, sms.getDate());
                }
                catch (Exception e){
                    e.printStackTrace();
                }


                /* The following part works correctly !
                try {
                    SmsManager mng = SmsManager.getDefault();
                    mng.sendTextMessage(to.getText().toString(), null, text.getText().toString(), null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }*/
            }
        });



    }

}

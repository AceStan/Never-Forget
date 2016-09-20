package com.acestan.neverforget.database;

import com.acestan.neverforget.models.Recipient;
import com.acestan.neverforget.models.SMS;

import java.util.Date;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public interface SMSViewer {
    public SMS viewSentSMS(Recipient r);
    public SMS viewSentSMS(Recipient r, Date d);
    public SMS viewSentSMS(Date d);
}

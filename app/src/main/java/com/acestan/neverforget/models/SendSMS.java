package com.acestan.neverforget.models;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class SendSMS {
    private int sms_id;
    private int recipient_id;

    public SendSMS(int sms_id, int email_id) {
        this.sms_id = sms_id;
        this.recipient_id = email_id;
    }

    public SendSMS() {
    }

    public int getSms_id() {
        return sms_id;
    }

    public void setSms_id(int sms_id) {
        this.sms_id = sms_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
    }
}

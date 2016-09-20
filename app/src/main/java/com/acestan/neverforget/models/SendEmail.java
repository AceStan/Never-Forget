package com.acestan.neverforget.models;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class SendEmail {
    private int email_id;
    private int recipient_id;

    public SendEmail(int email_id, int recipient_id) {
        this.email_id = email_id;
        this.recipient_id = recipient_id;
    }

    public SendEmail() {
    }

    public int getEmail_id() {
        return email_id;
    }

    public void setEmail_id(int email_id) {
        this.email_id = email_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
    }
}

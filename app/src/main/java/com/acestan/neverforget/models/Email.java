package com.acestan.neverforget.models;

import java.util.Date;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class Email {

    private int _id;
    private String subject;
    private String body;
    private String date;

    public Email( String subject, String body, String date) {

        this.subject = subject;
        this.body = body;
        this.date = date;
    }

    public Email(String subject, String body) {
        this.subject = subject;
        this.body = body;

    }

    public Email() {
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.subject;
    }
}

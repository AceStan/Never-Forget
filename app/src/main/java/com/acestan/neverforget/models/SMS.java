package com.acestan.neverforget.models;

import java.util.Date;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class SMS {

    private int _id;
    private String title;
    private String content;
    private String date;

    public SMS(String title, String content, String date) {

        this.title = title;
        this.content = content;
        this.date = date;
    }

    public SMS(String title, String content) { // Constructor without defined date
        this.title = title;
        this.content = content;
    }

    public SMS() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.content;
    }
}

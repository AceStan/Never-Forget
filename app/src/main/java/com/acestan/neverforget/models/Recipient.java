package com.acestan.neverforget.models;

/**
 * Created by aleksandar.stanoevsk on 9/20/2016.
 */
public class Recipient {


    private String mobile;
    private String eMail;

    public Recipient(String mobile, String eMail) {

        this.mobile = mobile;
        this.eMail = eMail;
    }

    public Recipient() {
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}

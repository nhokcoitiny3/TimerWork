package com.example.nhokc.project2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Job {
    String title;
    String content;
    Date date;
    Date time;

    public Job(String title, String content, Date date, Date time) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    public String getDateFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }

    public String getHourFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("hh:mm a", Locale.getDefault());
        return dft.format(d);
    }
    public String toString() {
        return this.title+"-"+
                getDateFormat(this.date)+"-"+
                getHourFormat(this.time);
    }
}

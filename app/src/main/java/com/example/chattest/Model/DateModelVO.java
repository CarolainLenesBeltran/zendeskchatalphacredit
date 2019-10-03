package com.example.chattest.Model;

public class DateModelVO {

    private final String title;
    private final long dateInMillis;

    public DateModelVO(String title, long dateInMillis ) {
        this.title = title;
        this.dateInMillis = dateInMillis;
    }


    public String getTitle() {
        return title;
    }

    public long getDateInMillis() {
        return dateInMillis;
    }


}

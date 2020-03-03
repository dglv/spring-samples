package ru.dglv.springxmlcore;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Event {
    private long id;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.date = date;
        this.df = df;
    }

    public long getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[" +
                id + " " +
                msg + " " +
                df.format(date) +
                "]";
    }
}

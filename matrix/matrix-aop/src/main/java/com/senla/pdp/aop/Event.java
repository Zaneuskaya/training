package com.senla.pdp.aop;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

    private Date eventTime;

    private String action;

    private String status;

    private String user;

    public Event(Date eventTime, String action, String status, String user) {
        this.eventTime = eventTime;
        this.action = action;
        this.status = status;
        this.user = user;
    }
}

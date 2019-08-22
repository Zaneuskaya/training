package com.senla.pdp.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Event {

    @Id
    public ObjectId _id;

    public String action;

    public String status;

    public String user;

    public Event(){}

    public Event(ObjectId _id, String action, String status, String user) {
        this._id = _id;
        this.action = action;
        this.status = status;
        this.user = user;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

package com.gmail.jumpercorderosa.planetabuffet.model;

/**
 * Created by danielle on 13/08/2017.
 */

public class User {

    private int id;
    private String login;
    private String password;
    private String email;
    private String phone_number;
    private String facebook_id;
    private boolean active_flag;
    private String event_date;
    private int event_type_id;
    private int buffet_id;

    public User(){};

    public User(int id, String login, String password, String email, String phone_number,
                String event_date, int event_type_id, int buffet_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.facebook_id = facebook_id;
        //this.active_flag = true;
        this.event_date = event_date;
        this.event_type_id = event_type_id;
        this.buffet_id = buffet_id;
    }

    public String getEventDate() { return event_date; }

    public void setEventDate(String event_date) { this.event_date = event_date; }

    public int getEventTypeId() { return event_type_id; }

    public void setEventTypeId(int event_type_id) { this.event_type_id = event_type_id; }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFacebookId() {
        return facebook_id;
    }

    public void setFacebookId(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public boolean isActiveFlag() {
        return active_flag;
    }

    public void setActiveFlag(boolean active_flag) {
        this.active_flag = active_flag;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBuffetId() { return buffet_id; }

    public void setBuffetId(int buffet_id) { this.buffet_id = buffet_id; }


}

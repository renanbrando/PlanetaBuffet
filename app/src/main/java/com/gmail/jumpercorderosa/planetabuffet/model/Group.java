package com.gmail.jumpercorderosa.planetabuffet.model;

/**
 * Created by danielle on 13/08/2017.
 */

public class Group {

    private int group_id;
    private String group_desc;

    public Group(){};

    public Group(int id, String desc) {
        this.group_id = id;
        this.group_desc = desc;
    }

    public int getGroupId() {
        return group_id;
    }

    public void setGroupId(int group_id) {
        this.group_id = group_id;
    }

    public String getGroupDesc() {
        return group_desc;
    }

    public void setGroupDesc(String group_desc) {
        this.group_desc = group_desc;
    }

}

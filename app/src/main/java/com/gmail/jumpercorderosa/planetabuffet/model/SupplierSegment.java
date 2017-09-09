package com.gmail.jumpercorderosa.planetabuffet.model;

public class SupplierSegment {

    private int segment_id;
    private String segment_desc;
    private String segment_img_name;

    public SupplierSegment(){};

    public SupplierSegment(int segment_id, String segment_desc, String segment_img_name) {
        this.segment_id = segment_id;
        this.segment_desc = segment_desc;
        this.segment_img_name = segment_img_name;
    }

    public String getSegmentImgName() { return segment_img_name; }

    public void setSegmentImgName(String segment_img_name) { this.segment_img_name = segment_img_name; }

    public int getSupplierSegmentId() {
        return segment_id;
    }

    public void setSupplierSegmentId(int segment_id) {
        this.segment_id = segment_id;
    }

    public String getSupplierSegmentDesc() {
        return segment_desc;
    }

    public void setSupplierSegmentDesc(String segment_desc) {
        this.segment_desc = segment_desc;
    }

}

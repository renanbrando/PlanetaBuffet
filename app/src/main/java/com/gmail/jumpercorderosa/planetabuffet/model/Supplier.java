package com.gmail.jumpercorderosa.planetabuffet.model;

public class Supplier {

    private int supplier_id;
    private int segment_id;
    private int buffet_id;
    private String name;
    private String cnpj;
    private String contact;
    private String phone_number;

    public Supplier(){};

    public Supplier(int supplier_id, int segment_id, int buffet_id, String name,
                    String contact, String phone_number) {

        this.supplier_id = supplier_id;
        this.segment_id = segment_id;
        this.buffet_id = buffet_id;
        this.name = name;
        this.cnpj = cnpj;
        this.contact = contact;
        this.phone_number = phone_number;
    }


    public int getId() {return supplier_id;  }

    public void setId(int id) {this.supplier_id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSegmentId() {
        return segment_id;
    }

    public void setSegmentId(int segment_id) {
        this.segment_id = segment_id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getBuffetId() {
        return buffet_id;
    }

    public void setBuffetId(int buffet_id) {
        this.buffet_id = buffet_id;
    }

    public String getCnpj() { return cnpj; }

    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}

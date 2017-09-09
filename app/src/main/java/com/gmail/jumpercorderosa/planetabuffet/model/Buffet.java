package com.gmail.jumpercorderosa.planetabuffet.model;

public class Buffet {

    private int id;
    private int buffet_segment_id;
    private String subsidiary;
    private String address;
    private int number;
    private String cep;
    private String phone_number;
    private String cnpj;

    public Buffet(){};

    public Buffet(int id, int buffet_segment_id, String subsidiary, String address, int number, String cep,
                  String phone_number, String cnpj) {

        this.id = id;
        this.buffet_segment_id = buffet_segment_id;
        this.subsidiary = subsidiary;
        this.address = address;
        this.number = number;
        this.cep = cep;
        this.phone_number = phone_number;
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuffetSegmentId() {
        return buffet_segment_id;
    }

    public void setBuffetSegmentId(int buffet_segment_id) {
        this.buffet_segment_id = buffet_segment_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) { this.number = number; }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSubsidiary() { return subsidiary; }

    public void setSubsidiary(String subsidiary) { this.subsidiary = subsidiary; }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

package com.gmail.jumpercorderosa.planetabuffet.model;

public class UserSupplier {

    int user_id;
    int supplier_id;

    public UserSupplier(){};

    public UserSupplier(int user_id, int supplier_id) {
        this.user_id = supplier_id;
        this.supplier_id = supplier_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getSupplierId() {
        return supplier_id;
    }

    public void setSupplierId(int supplier_id) {
        this.supplier_id = supplier_id;
    }

}

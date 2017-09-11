package com.gmail.jumpercorderosa.planetabuffet.adapter;

public class IconData {

    private String description;
    private int imgId;
    private int supplier_id;
    private int supplier_segment_id;

    public IconData(String description, int imgId, int supplier_segment_id, int supplier_id ) {
        this.description = description;
        this.imgId = imgId;
        this.supplier_segment_id = supplier_segment_id;
        this.supplier_id = supplier_id;
    }

    public int getSupplierSegmentId() { return supplier_segment_id;    }

    public void setSupplierSegmentId(int supplier_segment_id) { this.supplier_segment_id = supplier_segment_id;    }

    public int getSupplierId() { return supplier_id;    }

    public void setSupplierId(int supplier_id) { this.supplier_id = supplier_id;    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}

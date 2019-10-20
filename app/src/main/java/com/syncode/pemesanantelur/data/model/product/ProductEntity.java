package com.syncode.pemesanantelur.data.model.product;

import com.google.gson.annotations.SerializedName;

public class ProductEntity {
    @SerializedName("id_product")
    private String idProduct;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("type")
    private String type;

    @SerializedName("price")
    private int harga;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String desc;

    public ProductEntity(String idProduct, String productName, String type, int harga,String desc, String image) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.type = type;
        this.harga = harga;
        this.desc = desc;
        this.image = image;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

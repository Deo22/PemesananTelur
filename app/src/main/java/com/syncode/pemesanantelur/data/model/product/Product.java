package com.syncode.pemesanantelur.data.model.product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("data_product")
    List<ProductEntity> listProduct;

    @SerializedName("message")
    private String message;

    public Product(List<ProductEntity> listProduct, String message) {
        this.listProduct = listProduct;
        this.message = message;
    }

    public List<ProductEntity> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductEntity> listProduct) {
        this.listProduct = listProduct;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

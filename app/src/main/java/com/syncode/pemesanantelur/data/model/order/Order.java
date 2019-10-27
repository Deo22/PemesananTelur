package com.syncode.pemesanantelur.data.model.order;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("image")
    private String image;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("total_transaksi")
    private int priceAll;

    @SerializedName("id_product")
    private String idProduct;

    @SerializedName("id_transaksi")
    private String idTransaksi;

    @SerializedName("id_order")
    private String idOrder;

    @SerializedName("id_courier")
    private String idCourier;

    @SerializedName("tgl")
    private String date;

    @SerializedName("status_order")
    private String status;

    @SerializedName("jumlah_order")
    private int countOrder;

    @SerializedName("courier_name")
    private String courierName;


    public Order(String image, String productName, int priceAll, String idProduct, String idTransaksi, String idOrder, String idCourier, String date, String status, int countOrder, String courierName) {
        this.image = image;
        this.productName = productName;
        this.priceAll = priceAll;
        this.idProduct = idProduct;
        this.idTransaksi = idTransaksi;
        this.idOrder = idOrder;
        this.idCourier = idCourier;

        this.date = date;
        this.status = status;
        this.countOrder = countOrder;
        this.courierName = courierName;
    }

    public String getImage() {
        return image;
    }

    public String getProductName() {
        return productName;
    }

    public int getPriceAll() {
        return priceAll;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getIdCourier() {
        return idCourier;
    }


    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public int getCountOrder() {
        return countOrder;
    }

    public String getCourierName() {
        return courierName;
    }
}

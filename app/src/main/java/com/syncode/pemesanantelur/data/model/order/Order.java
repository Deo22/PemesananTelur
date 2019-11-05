package com.syncode.pemesanantelur.data.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Order implements Parcelable {

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
    private int status;

    @SerializedName("jumlah_order")
    private int countOrder;

    @SerializedName("courier_name")
    private String courierName;

    @SerializedName("quality")
    private String quality;


    public Order(String image, String productName, int priceAll, String idProduct, String idTransaksi, String idOrder, String idCourier, String date, int status, int countOrder, String courierName, String quality) {
        this.image = image;
        this.productName = productName;
        this.priceAll = priceAll;
        this.quality = quality;
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

    public int getStatus() {
        return status;
    }

    public String getQuality() {
        return quality;
    }

    public int getCountOrder() {
        return countOrder;
    }

    public String getCourierName() {
        return courierName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.productName);
        dest.writeInt(this.priceAll);
        dest.writeString(this.idProduct);
        dest.writeString(this.idTransaksi);
        dest.writeString(this.idOrder);
        dest.writeString(this.idCourier);
        dest.writeString(this.date);
        dest.writeInt(this.status);
        dest.writeInt(this.countOrder);
        dest.writeString(this.courierName);
        dest.writeString(this.quality);
    }

    protected Order(Parcel in) {
        this.image = in.readString();
        this.productName = in.readString();
        this.priceAll = in.readInt();
        this.idProduct = in.readString();
        this.idTransaksi = in.readString();
        this.idOrder = in.readString();
        this.idCourier = in.readString();
        this.date = in.readString();
        this.status = in.readInt();
        this.countOrder = in.readInt();
        this.courierName = in.readString();
        this.quality = in.readString();
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}

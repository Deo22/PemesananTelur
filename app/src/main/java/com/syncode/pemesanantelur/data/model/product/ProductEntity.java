package com.syncode.pemesanantelur.data.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductEntity implements Parcelable {
    @SerializedName("id_product")
    private String idProduct;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("type")
    private String type;

    public String getType() {
        return type;
    }

    @SerializedName("price")
    private int harga;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String desc;


    public ProductEntity(String idProduct, String productName, String type, int harga, String desc, String image) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.type = type;
        this.harga = harga;
        this.desc = desc;
        this.image = image;
    }


    public String getProductName() {
        return productName;
    }


    public String getImage() {
        return image;
    }

    public int getHarga() {
        return harga;
    }


    public String getDesc() {
        return desc;
    }

    public String getIdProduct() {
        return idProduct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idProduct);
        dest.writeString(this.productName);
        dest.writeString(this.type);
        dest.writeInt(this.harga);
        dest.writeString(this.image);
        dest.writeString(this.desc);
    }

    protected ProductEntity(Parcel in) {
        this.idProduct = in.readString();
        this.productName = in.readString();
        this.type = in.readString();
        this.harga = in.readInt();
        this.image = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<ProductEntity> CREATOR = new Parcelable.Creator<ProductEntity>() {
        @Override
        public ProductEntity createFromParcel(Parcel source) {
            return new ProductEntity(source);
        }

        @Override
        public ProductEntity[] newArray(int size) {
            return new ProductEntity[size];
        }
    };
}

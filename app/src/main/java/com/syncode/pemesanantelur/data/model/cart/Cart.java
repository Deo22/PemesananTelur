package com.syncode.pemesanantelur.data.model.cart;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Cart.TABLE_NAME)
public class Cart implements Parcelable {
    static final String TABLE_NAME = "tb_cart";
    public static final String DB_NAME = "dbtelur";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private
    int idTrans;

    @ColumnInfo(name = "id_user")
    private String idUser;

    @ColumnInfo(name = "product_name")
    private
    String productName;

    @ColumnInfo(name = "price")
    private
    int price;

    @ColumnInfo(name = "ammount")
    private int ammount;

    public int getAmmount() {

        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public Cart(int idTrans, String idUser, String productName, int price, int ammount) {
        this.idTrans = idTrans;
        this.productName = productName;
        this.price = price;
        this.ammount = ammount;
        this.idUser = idUser;
    }

    public int getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(int idTrans) {
        this.idTrans = idTrans;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idTrans);
        dest.writeString(this.idUser);
        dest.writeString(this.productName);
        dest.writeInt(this.price);
        dest.writeInt(this.ammount);
    }

    protected Cart(Parcel in) {
        this.idTrans = in.readInt();
        this.idUser = in.readString();
        this.productName = in.readString();
        this.price = in.readInt();
        this.ammount = in.readInt();
    }

    public static final Parcelable.Creator<Cart> CREATOR = new Parcelable.Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel source) {
            return new Cart(source);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };
}

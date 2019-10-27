package com.syncode.pemesanantelur.data.model.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderEntity {

    @SerializedName("status")
    private String status;

    @SerializedName("data_transaksi")
    private List<Order> dataTransaction;

    @SerializedName("data_order")
    private List<Order> dataOrder;

    @SerializedName("count")
    private int rowCount;

    public OrderEntity(String status, List<Order> dataTransaction, int rowCount, List<Order> dataOrder) {
        this.status = status;
        this.dataOrder = dataOrder;
        this.rowCount = rowCount;
        this.dataTransaction = dataTransaction;
    }

    public int getRowCount() {
        return rowCount;
    }

    public String getstatus() {
        return status;
    }


    public List<Order> getDataOrder() {
        return dataOrder;
    }

    public String getStatus() {
        return status;
    }

    public List<Order> getDataTransaction() {
        return dataTransaction;
    }
}

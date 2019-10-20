package com.syncode.pemesanantelur.data.model.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderEntity {

    @SerializedName("message")
    private String message;

    @SerializedName("data_order")
    private List<Order> dataOrder;

    @SerializedName("count")
    private int rowCount;

    public OrderEntity(String message, List<Order> dataOrder,int rowCount) {
        this.message = message;
        this.dataOrder = dataOrder;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Order> getDataOrder() {
        return dataOrder;
    }

    public void setDataOrder(List<Order> dataOrder) {
        this.dataOrder = dataOrder;
    }
}

package com.syncode.pemesanantelur.data.model;

import com.google.gson.annotations.SerializedName;

public class ResponseChangeCount {

    @SerializedName("total")
    private String total;

    @SerializedName("status")
    private String status;


    public String getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}

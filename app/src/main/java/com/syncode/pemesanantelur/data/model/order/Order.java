package com.syncode.pemesanantelur.data.model.order;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("id_order")
    private String idOrder;

    @SerializedName("nama_product")
    private String namaProduct;

    @SerializedName("nama_agent")
    private String namaAgent;

    @SerializedName("nama_kurir")
    private String namaKurir;
    @SerializedName("keterangan")
    private String ket;
    @SerializedName("ulasan")
    private String ulasan;
    @SerializedName("alamat")
    private String alamat;

    @SerializedName("harga")
    private int harga;
    @SerializedName("jumlah")
    private int jumlah;
    @SerializedName("status_pembayaran")
    private int status;
    @SerializedName("tgl")
    private String tanggal;


    public Order(String idOrder, String namaProduct, String namaAgent, String namaKurir, String ket, String ulasan, String alamat, int harga, int jumlah, int status, String tanggal) {
        this.idOrder = idOrder;
        this.namaProduct = namaProduct;
        this.namaAgent = namaAgent;
        this.namaKurir = namaKurir;
        this.ket = ket;
        this.ulasan = ulasan;
        this.alamat = alamat;
        this.harga = harga;
        this.jumlah = jumlah;
        this.status = status;
        this.tanggal = tanggal;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    public String getNamaAgent() {
        return namaAgent;
    }

    public void setNamaAgent(String namaAgent) {
        this.namaAgent = namaAgent;
    }

    public String getNamaKurir() {
        return namaKurir;
    }

    public void setNamaKurir(String namaKurir) {
        this.namaKurir = namaKurir;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}

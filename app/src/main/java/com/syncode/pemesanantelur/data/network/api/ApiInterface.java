package com.syncode.pemesanantelur.data.network.api;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.model.login.Login;
import com.syncode.pemesanantelur.data.model.product.Product;
import com.syncode.pemesanantelur.data.model.register.ResponRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("users/login")
    Call<Login> login(@Field("username") String username, @Field("password") String password);


    @GET("product")
    Call<Product> getProduct();


    @FormUrlEncoded
    @POST("orders")
    Call<OrderEntity> order(
            @Field("idOrder") String idOrder,
            @Field("namaProduct") String namaProduct,
            @Field("jumlah") int jumlah,
            @Field("harga") int harga,
            @Field("tgl") String tgl,
            @Field("status") int status,
            @Field("namaAgent") String namaAgent,
            @Field("alamat") String alamat,
            @Field("namaKurir") String namaKurir,
            @Field("keterangan") String keterangan,
            @Field("ulasan") String ulasan
    );

    @GET("orders")
    Call<OrderEntity> getOrder();


    @FormUrlEncoded
    @POST("users/register")
    Call<ResponRegister> registerUser(@Field("username") String username, @Field("email") String email, @Field("password") String password, @Field("fname") String fname, @Field("lname") String lname);


    @FormUrlEncoded
    @POST("users/address")
    Call<ResponRegister> reRegisterUser(@Field("username") String username, @Field("street") String street, @Field("id_agent") String idAgent, @Field("name_shop") String nameShop, @Field("coordinate") String coodinate);

    @FormUrlEncoded
    @POST("users/forget")
    Call<MessageOnly> forgetPassword(@Field("email") String email);


    @FormUrlEncoded
    @POST("users/verifyotp")
    Call<MessageOnly> tokenPassword(@Field("email") String email, @Field("otp") String otp);
}

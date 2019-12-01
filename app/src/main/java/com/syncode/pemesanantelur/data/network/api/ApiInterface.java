package com.syncode.pemesanantelur.data.network.api;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.ResponseChangeCount;
import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.model.login.ResponseLogin;
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
    Call<ResponseLogin> login(@Field("username") String username, @Field("password") String password);


    @GET("product")
    Call<Product> getProduct();

    @FormUrlEncoded
    @POST("orders/removeorder")
    Call<MessageOnly> removeOrder(@Field("id_order") String idOrder);

    @FormUrlEncoded
    @POST("orders/changeentity")
    Call<ResponseChangeCount> changeCountTotal(@Field("id_product") String idProduct, @Field("entity") int count);


    @FormUrlEncoded
    @POST("orders")
    Call<MessageOnly> order(
            @Field("idOrder") String idOrder,
            @Field("username") String username,
            @Field("idAddress") String idAddress,
            @Field("idAgent") String idAgent,
            @Field("idProduct") String idProduct,
            @Field("jumlahOrder") int jumlah,
            @Field("tgl") String date
    );

    @GET("orders")
    Call<OrderEntity> getOrder();


    @FormUrlEncoded
    @POST("users/register")
    Call<ResponRegister> registerUser(@Field("username") String username, @Field("email") String email, @Field("password") String password, @Field("fname") String fname, @Field("lname") String lname, @Field("phone") String phone);


    @FormUrlEncoded
    @POST("users/address")
    Call<ResponRegister> reRegisterUser(@Field("username") String username, @Field("street") String street, @Field("id_agent") String idAgent, @Field("name_shop") String nameShop, @Field("coordinate") String coodinate);

    @FormUrlEncoded
    @POST("users/forget")
    Call<MessageOnly> forgetPassword(@Field("email") String email);


    @FormUrlEncoded
    @POST("users/verifyotp")
    Call<MessageOnly> tokenPassword(@Field("email") String email, @Field("otp") String otp);

    @FormUrlEncoded
    @POST("users/emailverification")
    Call<MessageOnly> getTokenVerification(@Field("email") String email);


    @FormUrlEncoded
    @POST("users/verifyotpemail")
    Call<MessageOnly> verificationEmail(@Field("email") String email, @Field("otp") String otp, @Field("username") String username);


    @FormUrlEncoded
    @POST("users/reset")
    Call<MessageOnly> resetPassword(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/changepassword")
    Call<MessageOnly> changePassword(@Field("username") String username, @Field("oldPassword") String oldPassword, @Field("newPassword") String newPassword);


    @FormUrlEncoded
    @POST("transaction")
    Call<OrderEntity> getOrder(@Field("username") String username);

    @FormUrlEncoded
    @POST("users/changeprofile")
    Call<MessageOnly> changeProfile(@Field("username")String username,
                                    @Field("lname")String lName,
                                    @Field("fname")String fName,
                                    @Field("nameShop") String nameShop,
                                    @Field("email") String email,
                                    @Field("address") String address,
                                    @Field("phone") String phone,
                                    @Field("coordinate") String coordinate
    );
}

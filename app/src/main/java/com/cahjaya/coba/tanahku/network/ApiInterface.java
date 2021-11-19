package com.cahjaya.coba.tanahku.network;

import com.cahjaya.coba.tanahku.model.HasilKomen;
import com.cahjaya.coba.tanahku.model.User;
import com.cahjaya.coba.tanahku.model.komen;
import com.cahjaya.coba.tanahku.model.pengaduan;
import com.cahjaya.coba.tanahku.network.response.UserResponse;
import com.cahjaya.coba.tanahku.utils.HasilDaftarPengaduan;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/auth/login")
    public Call<UserResponse> postLogin(@Field("email") String email,
                                        @Field("password") String password);

    @GET("api/auth/user-profile")
    public Call<User> getUser(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/auth/ubahpassword")
    public Call<User> postUbahpass(@Header("Authorization") String token,
                              @Field("current_password") String current_password,
                              @Field("password") String password,
                              @Field("confirm_password") String confirm_password);

    @POST("api/auth/refresh")
    public Call<UserResponse> refreshToken(@Header("Authorization") String token);

    @POST("api/auth/logout")
    public Call<UserResponse> logout(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/register")
    public Call<UserResponse> postRegister(@Field("name") String name,
                                        @Field("email") String email,
                                        @Field("password") String password,
                                        @Field("password_confirmation") String password_confirmation);

    @FormUrlEncoded
    @POST("api/pengaduans")
    public Call<pengaduan> postPengaduan(@Header("Authorization") String token,
                                            @Field("nohak") String nohak,
                                           @Field("noberkas") String noberkas,
                                           @Field("tahun_berkas") String tahun_berkas,
                                           @Field("alamat") String alamat,
                                           @Field("deskripsi") String deskripsi);

    @GET("api/pengaduans")
    public Call<HasilDaftarPengaduan> getPengaduan(@Header("Authorization") String token);

    @GET("api/unduhform")
    public Call<ResponseBody> getUnduhform(@Header("Authorization") String token);

    @GET("api/pengaduans/{id}")
    public Call<pengaduan> getPengaduanid(@Path("id") String id,
                                          @Header("Authorization") String token);

    @GET("api/pengaduankomens/{id}")
    public Call<HasilKomen> getPengaduanKomen(@Path("id") String id,
                                              @Header("Authorization") String token);
    @FormUrlEncoded
    @POST("api/pengaduankomens/{id}")
    public Call<komen> postPengaduanKomen(@Path("id") String id,
                                        @Header("Authorization") String token,
                                         @Field("komen") String komen);
}

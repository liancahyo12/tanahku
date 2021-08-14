package com.cahjaya.coba.tanahku.network;

import com.cahjaya.coba.tanahku.model.User;
import com.cahjaya.coba.tanahku.network.response.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/auth/login")
    public Call<UserResponse> postLogin(@Field("email") String email,
                                        @Field("password") String password);

    @GET("api/auth/me")
    public Call<User> getUser(@Header("Authorization") String token);

    @POST("api/auth/refresh")
    public Call<UserResponse> refreshToken(@Header("Authorization") String token);

}

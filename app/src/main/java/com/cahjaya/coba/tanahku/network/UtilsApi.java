package com.cahjaya.coba.tanahku.network;

public class UtilsApi {
    public static final String BASE_URL_API = "http://dev1.cahjaya.com";

    // Mendeklarasikan Interface BaseApiService
    public static ApiInterface getAPIService(){
        return ApiClient.getClient(BASE_URL_API).create(ApiInterface.class);
    }
}

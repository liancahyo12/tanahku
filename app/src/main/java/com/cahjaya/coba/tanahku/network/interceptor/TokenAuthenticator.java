package com.cahjaya.coba.tanahku.network.interceptor;

import android.content.Intent;

import com.cahjaya.coba.tanahku.LoginActivity;
import com.cahjaya.coba.tanahku.Tanahku;
import com.cahjaya.coba.tanahku.network.ApiClient;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.network.response.UserResponse;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;

public class TokenAuthenticator implements Interceptor{

    SharedPrefManager sharedPrefManager;

    public TokenAuthenticator() {

        sharedPrefManager = new SharedPrefManager(Tanahku.getContext());
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response mainResponse = chain.proceed(chain.request());
        Request mainRequest = chain.request();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        if ( mainResponse.code() == 401 || mainResponse.code() == 403 ) {
            String token = sharedPrefManager.getSPToken();
            retrofit2.Response<UserResponse> refreshToken = apiInterface.refreshToken(token).execute();
            if (refreshToken.isSuccessful()) {
                sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, "Bearer " +
                        refreshToken.body().getToken());
                Request.Builder builder = mainRequest.newBuilder().header("Authorization",
                        sharedPrefManager.getSPToken())
                        .method(mainRequest.method(), mainRequest.body());
                mainResponse = chain.proceed(builder.build());
            }

            //Jika tidak ingin refresh token dan langsung logout
//            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
//            Intent i = new Intent(MyApp.getContext(), LoginActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            MyApp.getContext().startActivity(i);

        } else if ( mainResponse.code() == 500 ){
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
            Intent i = new Intent(Tanahku.getContext(), LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Tanahku.getContext().startActivity(i);
        }

        return mainResponse;
    }

}

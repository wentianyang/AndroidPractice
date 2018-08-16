package com.wentianyang.base.network;

import android.support.annotation.Nullable;
import com.wentianyang.base.config.AppConfig.ConfigKey;
import com.wentianyang.base.config.ConfigManager;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Date 创建时间:  2018/8/10
 * @Author: YTW
 * @Description:
 **/

public class HttpCreator {

    private OkHttpClient createRetrofit() {

        // 用于输出网络请求和结果
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Level.BODY);

        return new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)     // 添加网络日志拦截器
            .retryOnConnectionFailure(true)         // 出现错误重新连接
            .connectTimeout(15, TimeUnit.SECONDS)   // 超时时间
            .readTimeout(15, TimeUnit.SECONDS)      // 超时时间
            .build();
    }

    public <T> T createService(final Class<T> clazz) {

        Object baseUrl = ConfigManager.getConfig(ConfigKey.BASE_URL);
        if (baseUrl == null) {
            throw new NullPointerException("host is not setting...");
        }

        Retrofit retrofit = new Retrofit.Builder()
            .client(createRetrofit())
            .baseUrl(baseUrl.toString())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        return retrofit.create(clazz);
    }

    // token 拦截器，让所有的请求的header都加上这个token
    Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request authorised = originalRequest.newBuilder()
                .header("Authorization", "")
                .build();
            return chain.proceed(authorised);
        }
    };

    Authenticator mAuthenticator = new Authenticator() {
        @Nullable
        @Override
        public Request authenticate(Route route, Response response) throws IOException {

            return null;
        }
    };


}

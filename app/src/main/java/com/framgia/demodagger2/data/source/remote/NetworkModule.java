package com.framgia.demodagger2.data.source.remote;

import android.app.Application;
import com.framgia.demodagger2.utils.dagger.AppScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    //public static final String LINK_URL = "http://172.16.36.126/RetrofitRX/"; // my home
    private static final String LINK_URL = "http://172.16.36.141/RetrofitRX/"; // framgia

    private static Retrofit sRetrofit;

    Application mApplication;

    public NetworkModule(Application application) {
        mApplication = application;
    }

    @AppScope
    @Provides
    Application provideApplication() {
        return mApplication;
    }

    /**
     * Cache is "bộ nhớ tạm :v"
     */
    @AppScope
    @Provides
    Cache provideOkHttpConnectCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @AppScope
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return okHttpClient;
    }

    @AppScope
    @Provides
    Retrofit getStudentDataRX(OkHttpClient okHttpClient) {
        if (sRetrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            sRetrofit = new Retrofit.Builder().baseUrl(LINK_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    @AppScope
    @Provides
    Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}

package com.example.administrator.fistdemo.http;

import com.example.administrator.fistdemo.http.config.HttpConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static RetrofitFactory mRetrofitFactory;
    private static  APIFunction mAPIFunction;
    private RetrofitFactory(){
        OkHttpClient mOkHttpClient= new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)//设置请求超时
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)//设置读超时
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)//设置写超时
                .addInterceptor(InterceptorUtil.tokenInterceptor())//添加token拦截器
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return null;
                    }
                })//添加处理和拦截http请求的拦截器
                .build();//创建OkHttpClient的对象


        Retrofit mRetrofir = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)//添加请求的URL
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)//
                .build();


        mAPIFunction= mRetrofir.create(APIFunction.class);
    }

    /**
     * 通过单例得到RetrofitFactory的对象
     * @return
     */
    public static RetrofitFactory getInstence(){
        if (mRetrofitFactory==null){
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null)
                    mRetrofitFactory = new RetrofitFactory();
            }

        }
        return mRetrofitFactory;
    }

    /**
     * 得到拼接请求体的类对象
     * @return
     */
    public  APIFunction API(){
        return mAPIFunction;
    }

}

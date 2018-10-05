package com.example.administrator.fistdemo.http;

import com.example.administrator.fistdemo.http.bean.ABean;
import com.example.administrator.fistdemo.http.bean.BaseEntity;
import com.example.administrator.fistdemo.http.config.URLConfig;
import java.util.List;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 拼接请求体的类
 */
public interface APIFunction {
    @GET(URLConfig.baidu_url)
    Observable<BaseEntity<ABean>> getBaidu(@Query("wd") String name);

    @POST(URLConfig.login_token_url)
    Call<String> loginByToken(@Query("mobile") String mobile, @Query("token") String cookie);

    //上传单张图片
    @POST("服务器地址")
    Observable<Object> imageUpload(@Part() MultipartBody.Part img);
    //上传多张图片
    @POST("服务器地址")
    Observable<Object> imagesUpload(@Part() List<MultipartBody.Part> imgs);

}

package com.example.administrator.fistdemo;

import android.os.Bundle;
import com.example.administrator.fistdemo.common.BaseActivity;
import com.example.administrator.fistdemo.http.RetrofitFactory;
import com.example.administrator.fistdemo.http.base.BaseObserver;
import com.example.administrator.fistdemo.http.bean.ABean;
import com.example.administrator.fistdemo.http.bean.BaseEntity;
import com.example.administrator.fistdemo.utils.UploadUtil;
import java.util.ArrayList;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
    }

    public void getData() {
        RetrofitFactory.getInstence().API()//通retofit对象调取拼接参数的类
                .getBaidu("我是中国人")//调用要拼接请求体的方法
                .compose(this.<BaseEntity<ABean>> setThread())//??
                .subscribe(new BaseObserver<ABean>() {
                    @Override
                    protected void onSuccees(BaseEntity<ABean> t) throws Exception {

                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });//得到请求的结果
    }

    public void upload(){
        String filepath="图片本地路径";
        UploadUtil.uploadImage(filepath, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void uploads(){
        ArrayList<String> listFilePath=new ArrayList<>();
        listFilePath.add("图片1路径");
        listFilePath.add("图片2路径");
        UploadUtil.uploadImages(listFilePath, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}

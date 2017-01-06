package com.midian.qualitycloud.retrofit;

import com.midian.qualitycloud.bean.MapBean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class HttpMethods {

    public static final String BASE_URL = "http://app.gzqts.gov.cn/QCApp/";
    private static int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private HttpService httpService;

    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(new LogInterceptor());
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        httpService = retrofit.create(HttpService.class);
    }

    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private class HttpResultFunc<T> implements Func1<HttpResult<T>,T>{

        @Override
        public T call(HttpResult<T> tHttpResult) {
            if(!"success".equals(tHttpResult.getRet())){
                try {
                    throw new Exception(tHttpResult.getRet());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return tHttpResult.getContent();
        }
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s);
    }

    public void getMapMarker(Subscriber<MapBean> subscriber,String client_key,String type,String left_top_lon,
                             String left_top_lat,String right_bottom_lon,String right_bottom_lat){
        Map<String,String> map=new HashMap<>();
        map.put("client_key",client_key);
        map.put("type",type);
        map.put("left_top_lon",left_top_lon);
        map.put("left_top_lat",left_top_lat);
        map.put("right_bottom_lon",right_bottom_lon);
        map.put("right_bottom_lat",right_bottom_lat);
        Observable<MapBean> observable = httpService.getMapMarker(map);
        toSubscribe(observable,subscriber);
    }

//    /**
//     * 热门主播
//     * @param subscriber
//     */
//    public void getHotLiveRadio(Subscriber<HotBean> subscriber,int page){
//        Map<String,String> map=new HashMap<>();
//        map.put("page",page+"");
//        Observable<HotBean> observable = httpService.getHotLiveRadio(map)
//                .map(new HttpResultFunc<HotBean>());
//        toSubscribe(observable,subscriber);
//    }
}

package com.wentianyang.androidpractice.mvp.presenter;

import android.content.Context;
import android.util.Log;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.wentianyang.androidpractice.model.GankItem;
import com.wentianyang.androidpractice.mvp.view.ToolbarView;
import com.wentianyang.androidpractice.service.ApiService;
import com.wentianyang.base.model.BaseModel;
import com.wentianyang.base.network.HttpCreator;
import com.wentianyang.base.rx.BaseError;
import com.wentianyang.base.rx.BaseSubscriber;
import com.wentianyang.base.rx.RxSchedulers;
import java.util.List;

/**
 * @Date 创建时间:  2018/8/20
 * @Author: YTW
 * @Description:
 **/

public class ToolbarPresenter extends MvpBasePresenter<ToolbarView> {

    private static final String TAG = "ToolbarPresenter";

    public void fetchData(Context context) {
        ApiService service = new HttpCreator().createService(ApiService.class);
        service.getGankData("福利", 10, 1)
            .compose(getView().<BaseModel<List<GankItem>>>bindLifecycle())
            .compose(RxSchedulers.<BaseModel<List<GankItem>>>schedulerWithProgress(context))
            .subscribeWith(new BaseSubscriber<List<GankItem>>() {
                @Override
                public void onSuccess(List<GankItem> s) {
                    Log.d(TAG, "onSuccess: " + s);
                    getView().onSuccess(s);
                }

                @Override
                public void onFail(BaseError error) {
                    Log.d(TAG, "onFail: " + error.getMessage());
                }
            });
    }

//    public void test() {
//        //循环发送数字
//        Observable.interval(0, 1, TimeUnit.SECONDS)
//            .subscribeOn(Schedulers.io())
//            .compose(getView().<Long>bindLifecycle())   //这个订阅关系跟Activity绑定，Observable 和activity生命周期同步
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                new Consumer() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        System.out.println("lifecycle--" + o.toString());
//                    }
//                });
//    }
}

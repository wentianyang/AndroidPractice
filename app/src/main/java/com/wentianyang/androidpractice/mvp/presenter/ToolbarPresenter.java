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
            .compose(RxSchedulers.<BaseModel<List<GankItem>>>scheduler(context))
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
}

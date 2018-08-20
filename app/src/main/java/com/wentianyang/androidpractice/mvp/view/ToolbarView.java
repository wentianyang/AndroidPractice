package com.wentianyang.androidpractice.mvp.view;

import com.wentianyang.androidpractice.model.GankItem;
import com.wentianyang.base.mvp.BaseView;
import java.util.List;

/**
 * @Date 创建时间:  2018/8/20
 * @Author: YTW
 * @Description:
 **/

public interface ToolbarView extends BaseView {

    void onSuccess(List<GankItem> s);
}

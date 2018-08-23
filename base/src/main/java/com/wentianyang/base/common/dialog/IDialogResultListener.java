package com.wentianyang.base.common.dialog;

/**
 * @Date 创建时间:  2018/8/23
 * @Author: YTW
 * @Description: 用于DialogFragmentHelper与逻辑层的数据交互
 **/

public interface IDialogResultListener<T> {

    void onDataResult(T result);
}

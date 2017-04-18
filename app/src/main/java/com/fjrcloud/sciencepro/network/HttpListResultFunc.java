package com.fjrcloud.sciencepro.network;

import android.text.TextUtils;

import com.fjrcloud.sciencepro.data.base.HttpListResut;
import com.fjrcloud.sciencepro.exception.ApiException;
import com.fjrcloud.sciencepro.utils.Constants;

import rx.functions.Func1;


/**
 * 用来统一处理Http的resultCode,并将HttpListResult的Data的content部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */

public class HttpListResultFunc<T> implements Func1<HttpListResut<T>, T> {

    @Override
    public T call(HttpListResut<T> httpListResult) {
        if (!TextUtils.equals(httpListResult.getCode(), Constants.SUCCESS_CODE)) {
            throw new ApiException(httpListResult.getMsg());
        }
        return httpListResult.getData().getContent();
    }
}

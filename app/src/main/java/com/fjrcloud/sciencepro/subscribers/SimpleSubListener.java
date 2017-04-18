package com.fjrcloud.sciencepro.subscribers;

/**
 * Created by greedy on 2017/4/6.
 */

public interface SimpleSubListener<T> {
    void onNext(T t);

    void onError(Throwable throwable);
}

package com.fjrcloud.sciencepro.subscribers;

/**
 * Created by greedy on 2017/4/6.
 */

public interface CompleteSubListener<T> {
    void onNext(T t);

    void onError(Throwable throwable);

    void onCompleted();
}

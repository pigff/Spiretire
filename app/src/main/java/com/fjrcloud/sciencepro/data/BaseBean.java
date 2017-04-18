package com.fjrcloud.sciencepro.data;

import java.io.Serializable;

/**
 * Created by lin on 2016/12/30.
 */

public class BaseBean<T> implements Serializable {

   private String code;
   private String msg;
   private T t;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

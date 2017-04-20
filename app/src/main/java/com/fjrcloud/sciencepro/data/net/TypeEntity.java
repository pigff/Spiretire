package com.fjrcloud.sciencepro.data.net;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/19.
 */

public class TypeEntity implements Serializable{

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

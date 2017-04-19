package com.fjrcloud.sciencepro.data.net;

import java.io.Serializable;

/**
 * Created by greedy on 2017/4/18.
 */

public class DepartmentEntity implements Serializable{

    private int id;
    private String name;
    private String phone;
    private String imgPath;
    private String responsibilities;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
}

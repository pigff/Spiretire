package com.fjrcloud.sciencepro.data.net;

/**
 * Created by Administrator on 2017/4/19.
 */

public class LeaderEntity {

    private int id;
    private String name;
    private String phoneNum;
    private String dutyName;
    private String dutyDescripte;
    private String photoPath;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyDescripte() {
        return dutyDescripte;
    }

    public void setDutyDescripte(String dutyDescripte) {
        this.dutyDescripte = dutyDescripte;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}

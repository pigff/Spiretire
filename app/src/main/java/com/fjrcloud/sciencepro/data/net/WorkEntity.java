package com.fjrcloud.sciencepro.data.net;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public class WorkEntity implements Serializable{

    private int id;
    private String name;
    private String addr;
    private String phone;
    private long createTime;
    private String filesPath;
    private List<GuideItemsEntity> guideItems;
    private TypeEntity department;


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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getFilePath() {
        return filesPath == null ? "" : filesPath;
    }

    public void setFilePath(String filePath) {
        this.filesPath = filePath;
    }

    public List<GuideItemsEntity> getGuideItems() {
        return guideItems;
    }

    public void setGuideItems(List<GuideItemsEntity> guideItems) {
        this.guideItems = guideItems;
    }

    public TypeEntity getDepartment() {
        return department;
    }

    public void setDepartment(TypeEntity department) {
        this.department = department;
    }
}

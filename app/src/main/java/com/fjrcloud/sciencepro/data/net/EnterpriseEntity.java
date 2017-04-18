package com.fjrcloud.sciencepro.data.net;

import java.util.List;

/**
 * Created by greedy on 2017/4/18.
 */

public class EnterpriseEntity {

    private int id;
    private String name;
    private String logoPath;
    private String phone;
    private String address;
    private double longitude;
    private double latitude;
    private String vrPath;
    private String imagePath;
    private String linkPath;
    private boolean link;
    private String introduce;
    private List<EnterpriseEntity> enterpriseCategorySet;

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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getVrPath() {
        return vrPath;
    }

    public void setVrPath(String vrPath) {
        this.vrPath = vrPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    public boolean isLink() {
        return link;
    }

    public void setLink(boolean link) {
        this.link = link;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<EnterpriseEntity> getEnterpriseCategorySet() {
        return enterpriseCategorySet;
    }

    public void setEnterpriseCategorySet(List<EnterpriseEntity> enterpriseCategorySet) {
        this.enterpriseCategorySet = enterpriseCategorySet;
    }
}

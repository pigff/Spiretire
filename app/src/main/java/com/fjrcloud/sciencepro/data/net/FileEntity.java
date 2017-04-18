package com.fjrcloud.sciencepro.data.net;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by greedy on 2017/4/18.
 */

public class FileEntity implements Parcelable {

    private int id;
    private String name;
    private String filesPath;
    private long createTime;

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

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.filesPath);
        dest.writeLong(this.createTime);
    }

    public FileEntity() {
    }

    protected FileEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.filesPath = in.readString();
        this.createTime = in.readLong();
    }

    public static final Parcelable.Creator<FileEntity> CREATOR = new Parcelable.Creator<FileEntity>() {
        @Override
        public FileEntity createFromParcel(Parcel source) {
            return new FileEntity(source);
        }

        @Override
        public FileEntity[] newArray(int size) {
            return new FileEntity[size];
        }
    };
}

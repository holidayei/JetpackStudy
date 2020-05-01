package com.holiday.jetpackstudy.room.entity;

import android.graphics.Bitmap;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_user")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    //不需要映射到表中
    @Ignore
    private Bitmap mPicture;

    @Ignore
    private boolean mSelected;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Bitmap getPicture() {
        return mPicture;
    }

    public void setPicture(Bitmap picture) {
        mPicture = picture;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (obj.getClass() != getClass()) return false;
        User user = ((User) obj);
        if (mId == user.getId() && TextUtils.equals(mName, user.getName())) {
            return true;
        }
        return false;
    }
}

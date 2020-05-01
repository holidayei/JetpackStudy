package com.holiday.jetpackstudy.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Goods extends BaseObservable {
    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;

    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private String details;

    private float price;

    public Goods(String name, String details, float price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
        //只通知本字段更新
        notifyPropertyChanged(com.holiday.jetpackstudy.BR.name);
    }

    @Bindable
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
        //通知所有字段更新
        notifyChange();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}

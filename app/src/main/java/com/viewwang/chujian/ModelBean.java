package com.viewwang.chujian;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/26.
 */
public class ModelBean implements Serializable {
    private int ResId;
    private String Title;
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getResId() {
        return ResId;
    }

    public void setResId(int resId) {
        ResId = resId;
    }




}

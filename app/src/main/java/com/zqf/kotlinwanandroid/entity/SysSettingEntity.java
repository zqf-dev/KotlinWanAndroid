package com.zqf.kotlinwanandroid.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author: zqf
 * Date: 2022/08/19
 */
public class SysSettingEntity implements MultiItemEntity {

    public String title;
    public String desc;
    public int type;
    private String tip;
    private boolean sv;

    public SysSettingEntity(String title, String desc, int type, boolean sv) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.sv = sv;
    }

    public SysSettingEntity(String title, String desc, int type, String tip) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.tip = tip;
    }

    public SysSettingEntity(String title, String desc, int type, String tip, boolean sv) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.tip = tip;
        this.sv = sv;
    }

    public boolean isSv() {
        return sv;
    }

    public void setSv(boolean sv) {
        this.sv = sv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}

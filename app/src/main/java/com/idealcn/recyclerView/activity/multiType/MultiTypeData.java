package com.idealcn.recyclerView.activity.multiType;

import java.io.Serializable;

/**
 * @author: guoning
 * @date: 2018/12/11 15:10
 * @description:
 */
public class MultiTypeData implements Serializable {
    private int type;
    private String desc;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

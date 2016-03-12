package com.example.operatingsystemexperiment.bean;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class PCB {
    static int UID = 0;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public PCB(String name) {
        this.uid = ++UID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", uid=" + uid ;
    }

    private String name;
    private int uid;
}

package com.example.operatingsystemexperiment.bean;

import com.example.operatingsystemexperiment.util.ColorUtil;

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

    public PCB(String name, int size) {
        this.uid = ++UID;
        this.name = name;
        this.size = size;
        this.color = ColorUtil.getColor(UID);
    }

    public int getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public int getAdress() {
        return adress;
    }

    public void setAdress(int adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", uid=" + uid +
                ", size=" + size + "kb"
                ;
    }

    @Override
    public boolean equals(Object o) {
        return this.uid == ((PCB)o).uid;
    }

    private String name;
    private int uid;
    private int size;
    private int color;
    private int adress;
}

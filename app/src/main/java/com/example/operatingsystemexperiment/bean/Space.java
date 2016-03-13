package com.example.operatingsystemexperiment.bean;

/**
 * Created by 山东娃 on 2016/3/13.
 */
public class Space implements Comparable<Space>{
    public int start;
    public int end;
    public int size;

    public Space(int start, int size) {
        this.start = start;
        this.size = size;
        this.end = start + size;
    }

    public void setStart(int start) {
        this.start = start;
        this.size = end - start;
    }

    public void setEnd(int end) {
        this.end = end;
        this.size = end - start;
    }

    @Override
    public String toString() {
        return "Space{" +
                "start=" + start +", end=" + end +
                '}';
    }

    @Override
    public int compareTo(Space another) {
        return size - another.size;
    }
}

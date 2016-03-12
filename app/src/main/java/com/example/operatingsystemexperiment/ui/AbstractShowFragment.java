package com.example.operatingsystemexperiment.ui;

import com.example.operatingsystemexperiment.bean.PCB;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public abstract class AbstractShowFragment extends BaseFragment {
    abstract public String getTitle();
    abstract public void add(PCB pcb);
    abstract public void remove(PCB pcb);

}

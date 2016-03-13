package com.example.operatingsystemexperiment.util;

import android.util.Log;

import com.example.operatingsystemexperiment.MyApplication;
import com.example.operatingsystemexperiment.bean.PCB;
import com.example.operatingsystemexperiment.bean.Space;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 山东娃 on 2016/3/13.
 */
public class MemoryManageUtil {
    private static MemoryManageUtil memoryManageUtil;
    private List<Space> mFreeSpace;
    private List<Space> mUserSpace;
    private MemoryManageUtil() {
        mFreeSpace = new ArrayList<>();
        mUserSpace = new ArrayList<>();
        mFreeSpace.add(new Space(0, MyApplication.getMaxMemorySize()));
    }

    public static MemoryManageUtil getSingle(){
        if(memoryManageUtil == null){
            memoryManageUtil = new MemoryManageUtil();
        }
        return memoryManageUtil;
    }

    public boolean distributionPCB(PCB pcb){
        Collections.sort(mFreeSpace);
        for(int i = 0; i < mFreeSpace.size(); i++){
            if(mFreeSpace.get(i).size >= pcb.getSize()){
                Space s = new Space(mFreeSpace.get(i).start, pcb.getSize());
                pcb.setAdress(s.start);
                LogUtil.i("fuck", String.valueOf(pcb.getAdress()));
                mUserSpace.add(s);
                if(mFreeSpace.get(i).size == pcb.getSize()){
                    mFreeSpace.remove(i);
                }else{
                    int start = mFreeSpace.get(i).start + pcb.getSize();
                    int end = mFreeSpace.get(i).end;
                    mFreeSpace.remove(i);
                    mFreeSpace.add(new Space(start, end - start));
                }
//                Log.i("fuck", "Free : " + mFreeSpace.toString());
//                Log.i("fuck", "User : " + mUserSpace.toString());
                return true;
            }
        }

        return false;
    }

    public void recoveryPCB(PCB pcb){
        for(Space space : mUserSpace){
            if(pcb.getAdress() == space.start){
                mUserSpace.remove(space);
                break;
            }
        }
        if(pcb.getAdress() == 0){
            //回收开头的内存
//            Log.i("fuck", "回收开头");
            int temp = pcb.getAdress() + pcb.getSize();
            Log.i("fuck", String.valueOf(temp));
            for(Space space : mFreeSpace){
                if(space.start == temp){
                    mFreeSpace.remove(space);
                    mFreeSpace.add(new Space(0, space.end));
//                    Log.i("fuck", "Free : " + mFreeSpace.toString());
//                    Log.i("fuck", "User : " + mUserSpace.toString());
                    return;
                }
            }
            return;
        }
        if(pcb.getAdress() + pcb.getSize() == MyApplication.getMaxMemorySize()){
            //回收结尾的内存
            int temp = pcb.getAdress();
            for(Space space : mFreeSpace){
                if(space.start == temp){
                    mFreeSpace.add(new Space(space.start, MyApplication.getMaxMemorySize()));
                    mFreeSpace.remove(space);
                    return;
                }
            }
//            Log.i("fuck", "回收结尾");
//            Log.i("fuck", "Free : " + mFreeSpace.toString());
//            Log.i("fuck", "User : " + mUserSpace.toString());
            return;
        }
        //回收中间的内存

        Space recySpace = new Space(pcb.getAdress(),pcb.getSize());
        Space pre = null, nex = null;
        for(Space space : mFreeSpace){
            if(space.start == recySpace.end){
                nex = space;
            }
            if(space.end == recySpace.start){
                pre = space;
            }
        }

        if(pre != null){
            recySpace.setStart(pre.start);
            mFreeSpace.remove(pre);
        }
        if(nex != null){
            recySpace.setEnd(nex.end);
            mFreeSpace.remove(nex);
        }


        mFreeSpace.add(recySpace);
//        Log.i("fuck", "Free : " + mFreeSpace.toString());
//        Log.i("fuck", "User : " + mUserSpace.toString());
    }

}

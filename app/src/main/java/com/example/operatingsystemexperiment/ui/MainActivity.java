package com.example.operatingsystemexperiment.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.operatingsystemexperiment.MyApplication;
import com.example.operatingsystemexperiment.R;
import com.example.operatingsystemexperiment.adapter.ViewPagerAdapter;
import com.example.operatingsystemexperiment.bean.PCB;
import com.example.operatingsystemexperiment.ui.base.AbstractShowFragment;
import com.example.operatingsystemexperiment.ui.base.BaseActivity;
import com.example.operatingsystemexperiment.util.Constant;
import com.example.operatingsystemexperiment.util.MemoryManageUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ViewPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private AbstractShowFragment mShowReadyFragment;
    private AbstractShowFragment mShowRuningFragment;
    private AbstractShowFragment mShowBlockFragment;
    private AbstractShowFragment mShowMemoryFragment;
    private List<PCB> mReadyList;
    private List<PCB> mRuningList;
    private List<PCB> mBlockList;
    private FloatingActionButton mAddPCBBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        findView();
        initToolBar();
        setListener();
        initView();
    }

    private void initData() {
        mReadyList = new ArrayList<>();
        mRuningList = new ArrayList<>();
        mBlockList = new ArrayList<>();
    }


    private void initView() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mShowReadyFragment = new ShowPCBFragment(Constant.READY);
        mShowBlockFragment = new ShowPCBFragment(Constant.BLOCK);
        mShowRuningFragment = new ShowPCBFragment(Constant.RUNING);
        mShowMemoryFragment = new ShowMemoryFragment();
        List<AbstractShowFragment> fragments = new ArrayList<>();
        fragments.add(mShowReadyFragment);
        fragments.add(mShowRuningFragment);
        fragments.add(mShowBlockFragment);
        fragments.add(mShowMemoryFragment);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setListener() {
        mAddPCBBtn.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    mShowMemoryFragment.updata();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void findView() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mAddPCBBtn = (FloatingActionButton) findViewById(R.id.mAddPCBBtn);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                startSettingActivity();
                break;
            case R.id.action_stop_all:
                stopAllPCB();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void stopAllPCB() {
        mBlockList.clear();
        mRuningList.clear();
        mReadyList.clear();
        if(mShowMemoryFragment.isResumed()){
            mShowMemoryFragment.updata();
        }
    }

    private void startSettingActivity() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mAddPCBBtn:
                View view = LayoutInflater.from(this).inflate(R.layout.dialog_input, null);
                final EditText nameInput = ((TextInputLayout)view.findViewById(R.id.input_pcb_name)).getEditText();
                final EditText sizeInput = ((TextInputLayout)view.findViewById(R.id.input_pcb_size)).getEditText();
                new AlertDialog.Builder(this).setTitle("创建新进程").setView(view).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String pcbName = nameInput.getText().toString();
                        String pcbSizeStr = sizeInput.getText().toString();
                        if(!pcbName.isEmpty() && !pcbSizeStr.isEmpty()){
                            int pcbSize = Integer.parseInt(pcbSizeStr);
                            PCB pcb = new PCB(pcbName, pcbSize);
                            if(!MemoryManageUtil.getSingle().distributionPCB(pcb)){
                                toast("抱歉,内存不足~");
                                return;
                            }
                            if(mRuningList.size() < MyApplication.getMaxPcbRuning()){
                                mShowRuningFragment.add(pcb);
                            }else{
                                mShowReadyFragment.add(pcb);
                            }
                            if(mShowMemoryFragment.isResumed()){
                                mShowMemoryFragment.updata();
                            }

                        }
                    }
                }).show();
                break;
        }
    }


    public List<PCB> getBlockList() {
        return mBlockList;
    }

    public List<PCB> getRuningList() {
        return mRuningList;
    }

    public List<PCB> getReadyList() {
        return mReadyList;
    }

    public void timeSliceOverPCB(PCB pcb){
        mRuningList.remove(pcb);
        mShowRuningFragment.updata();
        mReadyList.add(pcb);
        mShowReadyFragment.updata();
        dispatchPCB();
    }

    public void blockPCB(PCB pcb){
       mRuningList.remove(pcb);
       mShowRuningFragment.updata();
       mBlockList.add(pcb);
       mShowBlockFragment.updata();
       dispatchPCB();
    }

    public void awaken(PCB pcb){
        mBlockList.remove(pcb);
        mShowBlockFragment.updata();
        mReadyList.add(pcb);
        mShowReadyFragment.updata();
        dispatchPCB();
    }

    public void dispatchPCB(){
        while(mRuningList.size() < MyApplication.getMaxPcbRuning() && !mReadyList.isEmpty()){
            PCB pcb = mReadyList.remove(0);
            mRuningList.add(pcb);
            mShowRuningFragment.updata();
            mShowReadyFragment.updata();
        }
    }


}

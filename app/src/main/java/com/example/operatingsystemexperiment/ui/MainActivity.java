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

import com.example.operatingsystemexperiment.R;
import com.example.operatingsystemexperiment.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ViewPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private AbstractShowFragment mShowReadyFragment;
    private AbstractShowFragment mShowRuningFragment;
    private AbstractShowFragment mShowBlockFragment;
    private FloatingActionButton mAddPCBBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initToolBar();
        setListener();
        initView();
    }


    private void initView() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mShowReadyFragment = new ShowPCBFragment("就绪");
        mShowBlockFragment = new ShowPCBFragment("堵塞");
        mShowRuningFragment = new ShowPCBFragment("运行");
        List<AbstractShowFragment> fragments = new ArrayList<>();
        fragments.add(mShowReadyFragment);
        fragments.add(mShowBlockFragment);
        fragments.add(mShowRuningFragment);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setListener() {
        mAddPCBBtn.setOnClickListener(this);
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

    }

    private void startSettingActivity() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mAddPCBBtn:
                View view = LayoutInflater.from(this).inflate(R.layout.dialog_input, null);
                final EditText dataInput = ((TextInputLayout)view.findViewById(R.id.input_layout)).getEditText();
                new AlertDialog.Builder(this).setTitle("创建新进程").setView(view).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         String pcbName = dataInput.getText().toString();
                        toast(pcbName);
                    }
                }).show();
                break;
        }
    }
}

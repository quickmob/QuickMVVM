package com.lookballs.app.mvvm.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.view.dialog.TestBindingDialog;
import com.lookballs.app.mvvm.view.popup.TestBindingPopupWindow;
import com.lookballs.mvvm.core.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void btn1(View view) {
        startActivity(new Intent(activity, TestBindingActivity.class));
    }

    public void btn2(View view) {
        TestBindingDialog dialog = new TestBindingDialog(activity);
        dialog.show();
    }

    public void btn3(View view) {
        TestBindingPopupWindow popupWindow = new TestBindingPopupWindow(activity);
        popupWindow.setWindowBackgroundAlpha(0.5f);
        popupWindow.showAsDropDown(view);
    }

    public void btn4(View view) {
        startActivity(new Intent(activity, TestViewModelBindingActivity.class));
    }

    public void btn5(View view) {
        startActivity(new Intent(activity, TestAdapterActivity.class));
    }

    public void btn99(View view) {
        Toast.makeText(activity, "测试", Toast.LENGTH_SHORT).show();
    }
}
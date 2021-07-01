package com.lookballs.app.mvvm.view.activity;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;

import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.view.dialog.TestBindingDialog;
import com.lookballs.app.mvvm.view.popup.TestBindingPopupWindow;
import com.lookballs.mvvm.core.BaseActivity;

import java.util.Map;

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
        //跳转activity并回调结果
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent data = result.getData();
                int resultCode = result.getResultCode();
            }
        }).launch(new Intent(activity, TestAdapterActivity.class));
        //申请敏感权限(单个)
        registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    //用户同意了该权限
                } else {
                    //用户拒绝了该权限
                }

            }
        }).launch(Manifest.permission_group.STORAGE);
        //申请敏感权限(多个)
        registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                for (Map.Entry<String, Boolean> entry : result.entrySet()) {

                }
            }
        }).launch(new String[]{Manifest.permission_group.STORAGE, Manifest.permission_group.CAMERA});
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
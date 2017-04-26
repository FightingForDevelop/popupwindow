package com.xiaoxiao.popwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxiao.popwindow.utils.ActivityStackControlUtil;

/**
 * Created by zxx on 2017/4/6.
 *
 * @descript 引导页
 */

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ActivityStackControlUtil.add(this);
	}
	public void login(View view) {
		Intent intent = new Intent(this,LoginActivity.class);
		startActivityForResult(intent,10);
	}
}

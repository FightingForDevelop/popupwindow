package com.xiaoxiao.popwindow;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiaoxiao.popwindow.utils.ActivityStackControlUtil;
import com.xiaoxiao.popwindow.utils.JsonParser;


public class LoginActivity extends FragmentActivity {
	private MyPopupwindow myPopupWindow;
	private HonestClickListener mHonestClickListener;
	private LinearLayout root;
	private LoginRoot loginRoot;
	private Dialog dialog;
	private WindowManager.LayoutParams mLp;
	private int count = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActivityStackControlUtil.add(this);
		root = (LinearLayout) findViewById(R.id.root);
		dialog = new Dialog(LoginActivity.this, R.style.translucent_dialog);
		mLp = dialog.getWindow().getAttributes();
		mLp.alpha = 0.3f;//（0.0-1.0）透明度，黑暗度为lp.dimAmount=1.0f;
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
//					WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		dialog.getWindow().setAttributes(mLp);

//		mLp.alpha = 0.5f;//（0.0-1.0）透明度，黑暗度为lp.dimAmount=1.0f;
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				pop();
			}
		}, 20);
	}

	public void request(View view) {
		String result = "{\"VersionNumber\":\"2.4.6\",\"return\":\"0\",\"employeecode\":\"3AC6A2588A8C9461\"," +
				"\"portalname\":\"李成其\",\"message\":\"登录成功\",\"imageurl\":\"\"," +
				"\"auth\":\"4791216041a842d4b6efff81c8a6692dcd9d83d28034444db0b5e836337cf731\"," +
				"\"crmusername\":\"F0D6556523CC69B72701B51FD6F2A8AE\",\"IsVersion\":\"True\"," +
				"\"Version\":null,\"BuildNumber\":\"174\",\"build\":null,\"FileUrl\":null," +
				"\"result\":\"0\",\"code\":0,\"msg\":null,\"data\":null,\"facePermission\":\"True\"," +
				"\"voiceAssistant\":\"False\",\"IsNeedSign\":true,\"IsForceSign\":false," +
				"\"EmployeeCode_Honest\":null}";
		loginRoot = JsonParser.paserObject(result, LoginRoot.class);
		boolean isNeedSign = loginRoot.isNeedSign();
		boolean isForceSign = loginRoot.isForceSign();
		Log.e("result", "isNeedSign:" + isNeedSign + ",isForceSign:" + isForceSign);
	}

	public void pop() {
		//弹出pop
//点击时弹出PopupWindow，屏幕变暗
		mHonestClickListener = new HonestClickListener();
		int mHeight = root.getHeight();
		WindowManager wm = this.getWindowManager();
		int height = wm.getDefaultDisplay().getHeight();//整个屏幕的高度
		myPopupWindow = new MyPopupwindow(this, mHonestClickListener, mHeight);
//		myPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//			@Override
//			public void onDismiss() {
////				lightoff();
//			}
//		});
		myPopupWindow.setOutsideTouchable(true);
		myPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		myPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
		myPopupWindow.showAtLocation(View.inflate(this, R.layout.activity_main, null), Gravity.CENTER,
				0, height - mHeight);
//		lightoff();
		dialog.show();
		new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
//				lighton();
//				mLp.alpha = 1.0f;//（0.0-1.0）透明度，黑暗度为lp.dimAmount=1.0f;
				dialog.dismiss();
				return false;
			}
		}).sendEmptyMessageDelayed(0, 500);
	}

	/**
	 * @date 2017/3/21
	 * @desc 当popupWindow弹出是背景变暗
	 */
	private void lightoff() {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.3f;
		getWindow().setAttributes(lp);
	}

	/**
	 * @date 2017/3/22
	 * @desc PopupWindow消失时，使屏幕恢复正常
	 */
	private void lighton() {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 1.0f;
		getWindow().setAttributes(lp);
	}

	public class HonestClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
				case R.id.ll_agree://同意进入首页
					myPopupWindow.dismiss();
//					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//					startActivity(intent);
//					finish();
					break;
				case R.id.ll_return://关闭popwindow消失
					myPopupWindow.dismiss();
//					WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();

// 模糊度getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
//					WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//					dialog.getWindow().setAttributes(lp);
//
//					lp.alpha=0.5f;//（0.0-1.0）透明度，黑暗度为lp.dimAmount=1.0f;
//					mLp.alpha = 0.3f;
					dialog.show();
					root.postDelayed(new Runnable() {
						@Override
						public void run() {
							Intent intent1 = new Intent(LoginActivity.this, SplashActivity.class);
							LoginActivity.this.setResult(20, intent1);
							LoginActivity.this.finish();
//							dialog.dismiss();
						}
					}, 500);
					new Handler().postDelayed(new Runnable()
					{
						public void run()
						{
							dialog.dismiss();
						}
					}, 600);

					break;
			}
		}
	}

	// 双击退出程序
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (myPopupWindow != null && myPopupWindow.isShowing()) {
				return true;
			}
			if (count == 0) {
				Toast.makeText(this,"再按一下退出应用",Toast.LENGTH_SHORT).show();
			}
			count++;
			if (count == 2) {
				//关闭应用
				ActivityStackControlUtil.exitApp();
				finish();
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}

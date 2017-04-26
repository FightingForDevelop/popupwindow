package com.xiaoxiao.popwindow;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 *
 * @descript 自定义popupwindow设置指定的弹出样式,背景为黑色
 */

public class MyPopupwindow extends PopupWindow {
	private int mWidth;//pop的宽度
	private int mHeight;//pop的高度
	private View mContentView;//添加布局
	private ImageView back,iv_close,iv_agree;//布局中的返回按钮关闭的图片同意的图片
	private TextView tv_common_title,tv_close,tv_promise;//标题,关闭内容,承若内容
	private LinearLayout ll_return,ll_agree,ll_right_agree;//点击事件
	private LoginActivity.HonestClickListener honestClickListener;//点击监听
	public MyPopupwindow(Context context, LoginActivity.HonestClickListener honestClickListener, int height) {
		super(context);
		this.honestClickListener = honestClickListener;
		//计算宽度和高度
		calWidthAndHeight(context);
		setWidth(mWidth);
		mHeight = height;
		setHeight(mHeight);
		mContentView= LayoutInflater.from(context).inflate(R.layout.activity_honest_statement,null,true);
		initView();
		//设置布局与相关属性
		setContentView(mContentView);
//		setFocusable(true);
		setTouchable(true);
		setBackgroundDrawable(null);
		setTouchInterceptor(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//点击PopupWindow以外区域时PopupWindow消失
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					dismiss();
				}
				return false;
			}
		});

	}
	private void initView() {
//		mContentView.findViewById(R.id.myroot).setFocusable(true);
//		mContentView.findViewById(R.id.myroot).setFocusableInTouchMode(true);
		/*mContentView.findViewById(R.id.myroot).setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.e("zxx","点击了返回键");
//				if (event.getAction()== KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
//					return true;
//				}
				return false;
			}
		});*/
		back = (ImageView) mContentView.findViewById(R.id.back);
		back.setVisibility(View.INVISIBLE);
		tv_common_title = (TextView) mContentView.findViewById(R.id.tv_common_title);
		tv_close = (TextView) mContentView.findViewById(R.id.tv_close);
		tv_promise = (TextView) mContentView.findViewById(R.id.tv_promise);
		iv_close = (ImageView) mContentView.findViewById(R.id.iv_close);
		iv_agree = (ImageView) mContentView.findViewById(R.id.iv_agree);
		iv_agree.setVisibility(View.GONE);
		tv_common_title.setText(R.string.honest_state);
		tv_close.setText(R.string.honest_close);
		tv_promise.setText(R.string.honest_promise);
		iv_close.setImageResource(R.mipmap.honest_close);

		ll_agree = (LinearLayout) mContentView.findViewById(R.id.ll_agree);
		ll_return = (LinearLayout) mContentView.findViewById(R.id.ll_return);
		ll_right_agree = (LinearLayout) mContentView.findViewById(R.id.ll_right_agree);
		ll_agree.setOnClickListener(honestClickListener);
		ll_return.setOnClickListener(honestClickListener);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ll_right_agree.getLayoutParams());
		lp.setMargins(0, 0, 0, 0);
		ll_right_agree.setLayoutParams(lp);
		LinearLayout.LayoutParams lp_promise = new LinearLayout.LayoutParams(tv_promise.getLayoutParams());
		lp_promise.setMargins(0, 0, 0, 0);
		tv_promise.setLayoutParams(lp_promise);

	}

	/**
	 * 设置PopupWindow的大小
	 * @param context
	 */
	private void calWidthAndHeight(Context context) {
		WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics= new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);

		mWidth=metrics.widthPixels;
	}
}
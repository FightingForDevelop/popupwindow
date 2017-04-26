package com.xiaoxiao.popwindow.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * @creator zxx
 * @date 2017/4/26
 * @desc activity栈堆控制
 */

public class ActivityStackControlUtil {
	public static List<Activity> getActivityList() {
		return activityList;
	}

	public static void setActivityList(List<Activity> activityList) {
		ActivityStackControlUtil.activityList = activityList;
	}

	private static List<Activity> activityList = new ArrayList<Activity>();

	public static void remove(Activity activity) {
		activityList.remove(activity);
	}

	public static void add(Activity activity) {
		activityList.add(activity);
	}

	//退出程序关闭所有activiy
	public static void exitApp() {
		for (Activity activity : activityList) {
			System.out.println("======================");
			System.out.println(activity.getLocalClassName());
			activity.finish();

		}
		activityList.clear();
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	
	public static void clearAcitity(){
		for (Activity activity : activityList) {
			System.out.println("======================");
			System.out.println(activity.getLocalClassName());
			activity.finish();

		}
		activityList.clear();
	}
}

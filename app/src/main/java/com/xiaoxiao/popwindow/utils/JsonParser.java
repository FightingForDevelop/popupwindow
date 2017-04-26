package com.xiaoxiao.popwindow.utils;

import android.os.Environment;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonParser {


	public static <T> T paserObject(String json, Class<T> t) {
		T result = null;
		try {
			result = JSONObject.parseObject(json, t);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//将json对象转换成T类
	public static <T> T paserObject(JSONObject jsonObject, Class<T> t) {
		T result = null;
		result = paserObject(jsonObject.toJSONString(), t);
		return result;
	}


	public static <T> List<T> paserArray(JSONArray jsonArray, Class<T> t) {
		List<T> result = null;
		try {
			result = JSONObject.parseArray(jsonArray.toJSONString(), t);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public static <T> T paserObject(InputStream is, Class<T> t) {
		String json = changeis2String(is);
		return paserObject(json, t);
	}


	public static <T> T paserObject(Object object, Class<T> t) {
		String json = "";
		if(object!=null)
		{
			json = object.toString();
		}
		return paserObject(json, t);
	}


	public static <T> List<T> paserArray(Object object, Class<T> t) {
		List<T> result = null;
		if(object!=null)
		{
			try {
				result = JSONObject.parseArray(object.toString(), t);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}


	public static String changeis2String(InputStream is) {
		if (is == null) {
			return null;
		}
		else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			try {
				while ((len = is.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					is.close();
					bos.close();
				}
				catch (IOException e) {
//					e.printStackTrace();
				}
			}
			return bos.toString().trim();
		}
	}

	public static String getFileString(String name) {
		String result = null;
		String path = Environment.getExternalStorageDirectory().getAbsolutePath();
		String filePath = path + File.separator + name;
		try {
			result = changeis2String(new FileInputStream(filePath));
		}
		catch (FileNotFoundException e) {
//			e.printStackTrace();
		}
		return result;
	}


	public static String parseIatResult(String json) {
		StringBuffer ret = new StringBuffer();
		try {
			JSONTokener tokener = new JSONTokener(json);
			org.json.JSONObject joResult = new org.json.JSONObject(tokener);
			org.json.JSONArray words = joResult.getJSONArray("ws");
			for (int i = 0; i < words.length(); i++) {
				// 转写结果词，默认使用第一个结果
				org.json.JSONArray items = words.getJSONObject(i).getJSONArray("cw");
				org.json.JSONObject obj = items.getJSONObject(0);
				ret.append(obj.getString("w"));
//				如果需要多候选结果，解析数组其他字段
//				for(int j = 0; j < items.length(); j++)
//				{
//					JSONObject obj = items.getJSONObject(j);
//					ret.append(obj.getString("w"));
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret.toString();
	}

	public static String parseGrammarResult(String json) {
		StringBuffer ret = new StringBuffer();
		try {
			JSONTokener tokener = new JSONTokener(json);
			org.json.JSONObject joResult = new org.json.JSONObject(tokener);

			org.json.JSONArray words = joResult.getJSONArray("ws");
			for (int i = 0; i < words.length(); i++) {
				org.json.JSONArray items = words.getJSONObject(i).getJSONArray("cw");
				for(int j = 0; j < items.length(); j++)
				{
					org.json.JSONObject obj = items.getJSONObject(j);
					if(obj.getString("w").contains("nomatch"))
					{
						ret.append("没有匹配结果.");
						return ret.toString();
					}
					ret.append("【结果】" + obj.getString("w"));
					ret.append("【置信度】" + obj.getInt("sc"));
					ret.append("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret.append("没有匹配结果.");
		}
		return ret.toString();
	}

	public static String parseLocalGrammarResult(String json) {
		StringBuffer ret = new StringBuffer();
		try {
			JSONTokener tokener = new JSONTokener(json);
			org.json.JSONObject joResult = new org.json.JSONObject(tokener);

			org.json.JSONArray words = joResult.getJSONArray("ws");
			for (int i = 0; i < words.length(); i++) {
				org.json.JSONArray items = words.getJSONObject(i).getJSONArray("cw");
				for(int j = 0; j < items.length(); j++)
				{
					org.json.JSONObject obj = items.getJSONObject(j);
					if(obj.getString("w").contains("nomatch"))
					{
						ret.append("没有匹配结果.");
						return ret.toString();
					}
					ret.append("【结果】" + obj.getString("w"));
					ret.append("\n");
				}
			}
			ret.append("【置信度】" + joResult.optInt("sc"));

		} catch (Exception e) {
			e.printStackTrace();
			ret.append("没有匹配结果.");
		}
		return ret.toString();
	}

}

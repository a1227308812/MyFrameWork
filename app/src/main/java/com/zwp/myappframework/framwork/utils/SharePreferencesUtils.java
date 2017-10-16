package com.zwp.myappframework.framwork.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by wang-dev
 */
public class SharePreferencesUtils {

	/**
	 * 保存数据
	 * @param context
	 * @param fileName
	 * @param key
     * @param object
     */
	public static void put(Context context, String fileName, String key, Object object) {

		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}
		editor.apply();
	}

	/**
	 * 得到数据
	 * @param context
	 * @param fileName
	 * @param key
	 * @param defaultObject
     * @return
     */
	public static Object get(Context context, String fileName, String key, Object defaultObject) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		if (defaultObject instanceof String) {
			return sp.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long) {
			return sp.getLong(key, (Long) defaultObject);
		}
		return null;
	}

	/**
	 * 移除某个key值已经对应的值
	 * @param context
	 * @param fileName
	 * @param key
     */
	public static void remove(Context context, String fileName, String key) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		editor.apply();
	}

	/**
	 * 清除所有数据
	 * @param context
	 * @param fileName
     */
	public static void clear(Context context, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.apply();
	}

	/**
	 * 查询某个key是否已经存在
	 * @param context
	 * @param fileName
	 * @param key
     * @return
     */
	public static boolean contains(Context context, String fileName, String key) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sp.contains(key);
	}

	/**
	 * 返回所有的键值对
	 * @param context
	 * @param fileName
     * @return
     */
	public static Map<String, ?> getAll(Context context, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sp.getAll();
	}
	
}
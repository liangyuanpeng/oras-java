package io.github.liangyuanpeng.oras.core.util;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

public class JSONUtils {

	/**
	 * Bean对象转JSON
	 * 
	 * @param object
	 * @return
	 */
	public static String beanToJson(Object object) {
		if (object != null) {
			Gson gson = new Gson();
			return gson.toJson(object);
		} else {
			return null;
		}
	}

	public static Object jsonToBean(String json, Class clazz) {
		if (StringUtils.isEmpty(json) || clazz == null) {
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(json,clazz);
	}
}

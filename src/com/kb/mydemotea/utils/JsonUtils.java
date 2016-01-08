package com.kb.mydemotea.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
	// 为头条页解析数据
	public static List<Map<String, Object>> getListFromJson(String json) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			JSONObject object = new JSONObject(json);
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				map = new HashMap<String, Object>();
				JSONObject object2 = array.getJSONObject(i);
				String id = object2.optString("id");
				String title = object2.optString("title");
				String source = object2.optString("source");
				String description = object2.optString("description");
				String wap_thumb = object2.optString("wap_thumb");
				String create_time = object2.optString("create_time");
				String nickname = object2.optString("nickname");

				map.put("id", id);
				map.put("title", title);
				map.put("source", source);
				map.put("description", description);
				map.put("wap_thumb", wap_thumb);
				map.put("create_time", create_time);
				map.put("nickname", nickname);

				list.add(map);
			}
			return list;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 为幻灯片页解析数据
	public static List<Map<String, Object>> getListFromJson2(String json) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			JSONObject object = new JSONObject(json);
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				map = new HashMap<String, Object>();
				JSONObject object2 = array.getJSONObject(i);
				String id = object2.optString("id");
				String title = object2.optString("title");
				String name = object2.optString("name");
				String link = object2.optString("link");
				String content = object2.optString("content");
				String image = object2.optString("image");
				String image_s = object2.optString("image_s");

				map.put("id", id);
				map.put("title", title);
				map.put("name", name);
				map.put("link", link);
				map.put("content", content);
				map.put("image", image);
				map.put("image_s", image_s);

				list.add(map);
			}
			return list;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 从webjson中获取list
	public static Map<String, Object> getMapFromWebJson(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject object = new JSONObject(json);
			JSONObject data = object.optJSONObject("data");
			String id = data.optString("id");
			String title = data.optString("title");
			String source = data.optString("source");
			String wap_content = data.optString("wap_content");
			String create_time = data.optString("create_time");
			String weiboUrl = data.optString("weiboUrl");

			map.put("id", id);
			map.put("title", title);
			map.put("source", source);
			map.put("wap_content", wap_content);
			map.put("create_time", create_time);
			map.put("weiboUrl", weiboUrl);

			return map;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

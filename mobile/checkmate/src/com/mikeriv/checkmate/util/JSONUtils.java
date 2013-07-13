package com.mikeriv.checkmate.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mikeriv
 * Date: 7/13/13
 * Time: 4:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class JSONUtils {

  public static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();

    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }


  public static Object toJSON(Object object) throws JSONException {
    if (object instanceof Map) {
      JSONObject json = new JSONObject();
      Map map = (Map) object;
      for (Object key : map.keySet()) {
        json.put(key.toString(), toJSON(map.get(key)));
      }
      return json;
    } else if (object instanceof Iterable) {
      JSONArray json = new JSONArray();
      for (Object value : ((Iterable) object)) {
        json.put(value);
      }
      return json;
    } else {
      return object;
    }
  }

  public static boolean isEmptyObject(JSONObject object) {
    return object.names() == null;
  }

  public static Map<String, Object> getMap(JSONObject object, String key) throws JSONException {
    return toMap(object.getJSONObject(key));
  }

  public static Map<String, Object> toMap(JSONObject object) throws JSONException {
    Map<String, Object> map = new HashMap();
    Iterator keys = object.keys();
    while (keys.hasNext()) {
      String key = (String) keys.next();
      map.put(key, fromJson(object.get(key)));
    }
    return map;
  }

  public static List toList(JSONArray array) throws JSONException {
    List list = new ArrayList();
    for (int i = 0; i < array.length(); i++) {
      list.add(fromJson(array.get(i)));
    }
    return list;
  }

  private static Object fromJson(Object json) throws JSONException {
    if (json == JSONObject.NULL) {
      return null;
    } else if (json instanceof JSONObject) {
      return toMap((JSONObject) json);
    } else if (json instanceof JSONArray) {
      return toList((JSONArray) json);
    } else {
      return json;
    }
  }

}



package com.nieqihao.haoweather.util;

import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nieqihao.haoweather.db.City;
import com.nieqihao.haoweather.db.County;
import com.nieqihao.haoweather.db.Province;


public class Utility {
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(response);
            JsonArray jsonArray = null;
            if (jsonElement.isJsonArray()) {
                jsonArray = jsonElement.getAsJsonArray();
            }
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                Province province = new Province();
                province.setProvinceCode(jo.get("id").getAsInt());
                province.setProvinceName(jo.get("name").getAsString());
                province.save();
            }

            return true;
        }
        return false;
    }

    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(response);
            JsonArray jsonArray = null;
            if (jsonElement.isJsonArray()) {
                jsonArray = jsonElement.getAsJsonArray();
            }
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                City city = new City();
                city.setCityCode(jo.get("id").getAsInt());
                city.setCityName(jo.get("name").getAsString());
                city.setProvinceId(provinceId);
                city.save();
            }
            return true;
        }
        return false;
    }

    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(response);
            JsonArray jsonArray = null;
            if (jsonElement.isJsonArray()) {
                jsonArray = jsonElement.getAsJsonArray();
            }
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                County county = new County();
                county.setWeatherId(jo.get("weather_id").getAsString());
                county.setCountyName(jo.get("name").getAsString());
                county.setCityId(cityId);
                county.save();
            }
            return true;
        }
        return false;
    }
}

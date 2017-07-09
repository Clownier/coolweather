package clown.com.coolweather.app.util;

import android.text.TextUtils;
import android.util.Log;

import clown.com.coolweather.app.db.CoolWeatherDB;
import clown.com.coolweather.app.model.City;
import clown.com.coolweather.app.model.County;
import clown.com.coolweather.app.model.Province;



public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(
            CoolWeatherDB coolWeatherDB,String response) {
        if(!TextUtils.isEmpty(response)){
            String [] allProvinves = response.split(",");
            if (allProvinves.length > 0){
                for (String p: allProvinves){
                    String [] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    Log.d("ProvinceName",array[1]);
                    //将解析的数据存储到Province表
                    coolWeatherDB.saveProvince(province);
                }
                return  true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(
            CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    Log.d("CityName", array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的County数据
     */
    public static boolean handleCountiesResponse(
            CoolWeatherDB coolWeatherDB,String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCounties;
            allCounties = response.split(",");
            if(allCounties.length > 0){
                for (String c:allCounties){
                    String[] array=c.split("\\|");
                    County county=new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    Log.d("CountyName",array[1]);
                    //将解析出来的数据存储到County表
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}

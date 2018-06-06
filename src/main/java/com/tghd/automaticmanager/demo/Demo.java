package com.tghd.automaticmanager.demo;

import com.tghd.automaticmanager.cmdb.interfaces.H3C.H3CHttpClientApiService;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        H3CHttpClientApiService apiService = new H3CHttpClientApiService();
        Map<String,String> map = new HashMap<String,String>();
        map.put("111","222");
        map.put("333","4444");
        try {
            apiService.toQuery("111",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

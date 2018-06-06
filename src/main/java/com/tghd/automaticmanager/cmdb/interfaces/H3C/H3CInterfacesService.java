package com.tghd.automaticmanager.cmdb.interfaces.H3C;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class H3CInterfacesService {
    @Autowired
    private H3CHttpClientApiService apiService;
    /*查询所有设备信息*/
    public String getAllDevice(){
        String returnXml = null;
        try {
            returnXml = apiService.toQuery("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnXml;
    }
    /*查询设备详细信息*/
    public String getDeviceMsg(String ip){
        String returnXml = null;
        Map<String,String> param = new HashMap<String,String>();
        param.put("ip",ip);
        try {
            returnXml = apiService.toQuery("",param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnXml;
    }
    /*查询设备资产信息*/
    public String getAsset(){
        return "";
    }
    /*查询设备管理账号信息*/
    public String getOperator(){
        return "";
    }
    /*查询设备端口信息*/
    public String getInterface(){
        return "";
    }

}

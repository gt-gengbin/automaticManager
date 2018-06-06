package com.tghd.automaticmanager.cmdb.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tghd.automaticmanager.database.ConnenctRestUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/matrix/entity")
public class M3DatabaseRestAction {

    @RequestMapping(value="/type")
    public String getCmdbType(){
        Map<String,String> successMap = new HashMap<String,String>();
        ConnenctRestUtil connenctRestUtil = ConnenctRestUtil.getinstance();
        String jsonSuccess = connenctRestUtil.search("call tree {\"ftype\":\"class\",\"parent\":\"/matrix/entity\"}");
        List<JSONObject> array = JSONArray.parseArray(jsonSuccess, JSONObject.class);
        if(!array.isEmpty()) {
            for (int i = 0; i <array.size(); i++) {
                List<JSONObject> tree = (List<JSONObject>) array.get(i).get("tree");
                if(!tree.isEmpty()){
                    for (int j = 0; j < tree.size(); j++) {
                       String alias = tree.get(j).getString("alias");
                       String name = tree.get(j).getString("name");
                        successMap.put(alias,name);
                    }
                }

            }
        }
        return JSON.toJSONString(successMap);
    }
    @RequestMapping(value="/{ci}" ,method = RequestMethod.GET)
    public String getCmdbInfoByIp(@PathVariable String ci,@RequestParam(value="ip",required=false) String ip){
        StringBuffer param = new StringBuffer("#/matrix/entity/");
        param.append(ci);
        if (ip!=null && !"".equals(ip)){
            param.append("| ip="+ip);
        }
        ConnenctRestUtil connenctRestUtil = ConnenctRestUtil.getinstance();
        String jsonSuccess = connenctRestUtil.search(param.toString());
        return jsonSuccess;
    }

    @RequestMapping(value="/{ci}" ,method = RequestMethod.GET)
    public String getCmdbInfoByHost(@PathVariable String ci,@RequestParam(value="host",required=false) String host){
        StringBuffer param = new StringBuffer("#/matrix/entity/");
        param.append(ci);
        if (host!=null && !"".equals(host)){
            param.append("| host="+host);
        }
        ConnenctRestUtil connenctRestUtil = ConnenctRestUtil.getinstance();
        String jsonSuccess = connenctRestUtil.search(param.toString());
        return jsonSuccess;
    }

    @RequestMapping(value="/{ci}" ,method = RequestMethod.GET)
    public String getCmdbInfoBySn(@PathVariable String ci,@RequestParam(value="sn",required=false) String sn){
        StringBuffer param = new StringBuffer("#/matrix/entity/");
        param.append(ci);
        if (sn!=null && !"".equals(sn)){
            param.append("| sn="+sn);
        }
        ConnenctRestUtil connenctRestUtil = ConnenctRestUtil.getinstance();
        String jsonSuccess = connenctRestUtil.search(param.toString());
        return jsonSuccess;
    }
}

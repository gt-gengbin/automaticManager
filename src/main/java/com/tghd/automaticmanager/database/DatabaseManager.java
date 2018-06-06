package com.tghd.automaticmanager.database;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseManager {

    @Autowired
    private ForwardUtil forward;
    private ConnenctRestUtil connenct = ConnenctRestUtil.getinstance();

    public String get (String cond){
        return connenct.search(cond);
    }

    public void save (String rule , List objs){
        try {
            forward.send(rule, JSONArray.toJSONString(objs));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void save (String rule , Object obj){
        List list = new ArrayList();
        list.add(obj);
        try {
            forward.send(rule, JSONArray.toJSONString(list));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

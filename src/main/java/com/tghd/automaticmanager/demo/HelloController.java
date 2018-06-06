package com.tghd.automaticmanager.demo;

import com.tghd.automaticmanager.cmdb.interfaces.H3C.H3CHttpClientApiService;
import com.tghd.automaticmanager.cmdb.interfaces.H3C.H3CInterfacesService;
import com.tghd.automaticmanager.cmdb.model.CmdbBasics;
import com.tghd.automaticmanager.database.DatabaseManager;
import com.tghd.automaticmanager.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class HelloController {
    @Autowired
    private DatabaseManager manager;
    @Autowired
    private H3CHttpClientApiService apiService;

    @RequestMapping(value = "/index")
    @ResponseBody
    public String hello() {
        //ConnenctRestUtil util = ConnenctRestUtil.getinstance();
        CmdbBasics cmdbBasics = new CmdbBasics();
        cmdbBasics.setAssetid("222");
        cmdbBasics.setBiz("biz");
        cmdbBasics.setCompany("company");
        cmdbBasics.setConfig(null);
        cmdbBasics.setContact("contact");
        cmdbBasics.setCtel("cter");
        cmdbBasics.setDc("dc");
        cmdbBasics.setDepartment("department");
        cmdbBasics.setElement(null);
        cmdbBasics.setFiles(null);
        cmdbBasics.setHost("222222222");
        cmdbBasics.setId("wwwwww");
        cmdbBasics.setIp("111.222.111.222");
        cmdbBasics.setLocation("cccccc");
        cmdbBasics.setModel("ddddd");
        cmdbBasics.setName("aaaaa");
        cmdbBasics.setPeriod(DateFormatUtil.nowDateToString("yyyy-MM-dd"));
        cmdbBasics.setRack("223eeee");
        cmdbBasics.setRegion("qqq2");
        cmdbBasics.setRoom("q123");
        cmdbBasics.setSn("sn");
        cmdbBasics.setStatus(0);
        cmdbBasics.setTel("tel");
        cmdbBasics.setType(0);
        cmdbBasics.setUnit("unit");
        cmdbBasics.setVtime(new Date());
        Map<String, String> config = new HashMap<String, String>();
        config.put("ceshi", "aaaa");
        config.put("ceshi11", "vvvv");
        config.put("ceshi22", "bbbbb");
        cmdbBasics.setConfig(config);
        List list = new ArrayList();
        list.add(cmdbBasics);
        //manager.save("rules.entity.oracle",list);


        return manager.get("#/matrix/entity/oracle");
    }
    @RequestMapping(value = "/index2")
    @ResponseBody
    public String h3cTest(){
        try {
            apiService.toQuery("11111");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

package com.tghd.automaticmanager.cmdb.interfaces.H3C;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Component
public class H3CHttpClientApiService {
    private static HttpClient client = null;
    @Autowired
    private H3Cconfig h3Cconfig;

    public void connect (){
        client = new HttpClient();
        HttpConnectionManagerParams params =
                client.getHttpConnectionManager().getParams();
        params.setConnectionTimeout(h3Cconfig.getConnectionTimeout());
        params.setSoTimeout(h3Cconfig.getSoTimeout());
        params.setSendBufferSize(h3Cconfig.getSendBufferSize());
        params.setReceiveBufferSize(h3Cconfig.getReceiveBufferSize());
        // 使用 HTTP 访问
        client.getHostConfiguration().setHost(h3Cconfig.getHost(), h3Cconfig.getPort());
        client.getState().setCredentials(
                new AuthScope(h3Cconfig.getHost(), h3Cconfig.getPort(), h3Cconfig.getRealm()),
                new UsernamePasswordCredentials(h3Cconfig.getUsername(), h3Cconfig.getPassword()));
    }

    public String toQuery(String url) throws Exception {
        if(client==null){
            this.connect();
        }
        String returnValue = "";
        GetMethod get = new GetMethod(url);
        get.addRequestHeader("accept", "application/xml");
        try {
            if (HttpStatus.SC_OK == client.executeMethod(get))
                returnValue = get.getResponseBodyAsString();
            get.releaseConnection();
        } finally {
            // 释放 GET 方法使用的所有资源
            get.releaseConnection();
        }
        return returnValue;
    }

    public String toQuery(String url, Map<String, String> param) throws Exception {
        if(client==null){
            this.connect();
        }
        String returnValue = "";
        GetMethod get = new GetMethod(url);
        get.addRequestHeader("accept", "application/xml");

        List<NameValuePair> nameValuePairList = new ArrayList();
        Iterator it = param.keySet().iterator();
        while (it.hasNext()) {
            NameValuePair valuePair = new NameValuePair();
            String key = (String) it.next();
            valuePair.setName(key);
            valuePair.setValue(param.get(key));
            nameValuePairList.add(valuePair);
        }
        NameValuePair[] nameValuePairArray =  nameValuePairList.toArray(new NameValuePair[]{new NameValuePair()});
        try {
            get.setQueryString(nameValuePairArray);
            if (HttpStatus.SC_OK == client.executeMethod(get))
                returnValue = get.getResponseBodyAsString();
            get.releaseConnection();
        } finally {
            // 释放 GET 方法使用的所有资源
            get.releaseConnection();
        }
        return returnValue;
    }
}

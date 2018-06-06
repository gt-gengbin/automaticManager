package com.tghd.automaticmanager.database;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class ConnenctRestUtil {

    private static Environment env;

    private static ConnenctRestUtil instance;
    private String host = null;
    private String name = null;
    private String password = null;
    private String company = null;
    private String sessionid = null;
    private final String STATUS_OK = "ok";
    private final String STATUS_SIGNIN = "signin";

    private ConnenctRestUtil() {
    }

    public static synchronized ConnenctRestUtil getinstance() {
        if (instance == null) {
            instance = new ConnenctRestUtil();
            instance.setHost(env.getProperty("omdbRest.host"));
            instance.setName(env.getProperty("omdbRest.name"));
            instance.setPassword(env.getProperty("omdbRest.password"));
            instance.setCompany(env.getProperty("omdbRest.company"));
            instance.setSessionid(instance.signin());
        }
        return instance;
    }

    /**
     * 登录数据库平台
     *
     * @return sessionid
     */
    private String signin() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", encode(getName()));
        map.put("password", encode(getPassword()));
        map.put("company", encode(getCompany()));

        String method = "/user/signin";
        String returnValue = connentMap(getHost(), method, map);

        JSONObject jsonObject = JSON.parseObject(returnValue);
        String status = jsonObject.get("status").toString();

        if (STATUS_OK.equals(status)) {
            return jsonObject.get("message").toString();
        } else {
            //抛出错误
            return null;
        }
    }

    /**
     * 一键搜索访问数据库
     *
     * @param cond
     * @return
     */
    public String search(String cond) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sessionid", getSessionid());
        map.put("cond", cond);

        String method = "/mxobject/search";
        String returnValue = connentMap(host, method, map);

        JSONObject jsonObject = JSON.parseObject(returnValue);
        String status = jsonObject.get("status").toString();

        //防止session失效，重新登录
        if (STATUS_SIGNIN.equals(status)) {
            this.setSessionid(this.signin());
            returnValue = search(cond);
        }
        if (STATUS_OK.equals(status)) {
            Object o = jsonObject.get("message");
            if (o != null)
                returnValue = jsonObject.get("message").toString();
            else
                returnValue = "查询失败";
        }

        return returnValue;
    }

    /**
     * 编码转换
     *
     * @param str
     */
    private String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 进行调用远程接口
     *
     * @param host
     * @param method
     * @param params
     * @return
     */
    private String connent(String host, String method, String params) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(host + method);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
// 可以根据请求的需要设置参数
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false); // 是否可以使用缓存 不使用缓存
            conn.setConnectTimeout(5000);// 请求超时时间
// 设置通用的请求属性 消息报头 即设置头字段
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
// 设置请求正文 即要提交的数据
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                    conn.getOutputStream()));
            pw.print(params);
            pw.flush();
            pw.close();
// 使用 connect 方法建立到远程对象的实际连接。
            conn.connect();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line = null;
            while (null != (line = br.readLine())) {
                buffer.append(line);
            }
            br.close();
            isr.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 将参数转化字符串后进行查询
     *
     * @param host
     * @param method
     * @param params
     * @return
     */
    private String connentMap(String host, String method, Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        sb.append("1=1");
        if (!params.isEmpty()) {
            Iterator<String> paramit = params.keySet().iterator();
            while (paramit.hasNext()) {
                String key = paramit.next();
                String value = params.get(key);
                if (value != null && !"".equals(value)) {
                    sb.append("&").append(key).append("=").append(encode(value));
                }
            }
        }

        return connent(host, method, sb.toString());
    }

    private void setHost(String host) {
        this.host = host;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setCompany(String company) {
        this.company = company;
    }

    private void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getHost() {
        return host;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
    }

    public String getSessionid() {
        return sessionid;
    }

    public static Environment getEnv() {
        return env;
    }

    @Autowired
    public void setEnv(Environment env) {
        ConnenctRestUtil.env = env;
    }
}

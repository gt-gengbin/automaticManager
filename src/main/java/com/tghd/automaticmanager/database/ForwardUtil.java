package com.tghd.automaticmanager.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class ForwardUtil {
    @Autowired
    private Environment env;



    public String send(String rule, String content) throws UnsupportedEncodingException {

        String host = env.getProperty("forward.host");
        String username = env.getProperty("forward.nameuser");
        String password = env.getProperty("forward.password");

        String forwardString = host + "/agent/" + username + "@" +
                password + "/" + rule + "?delimiter=" + URLEncoder.encode(",", "UTF-8") + "&filetype=json";

        String returnV = forWardSend(forwardString, content);

        return returnV;
    }

    private String forWardSend(String host, String params) {
        try {
            URL url = new URL(host);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 可以根据请求的需要设置参数
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false); // 是否可以使用缓存 不使用缓存
            connection.setConnectTimeout(5000);// 请求超时时间
            // 设置通用的请求属性 消息报头 即设置头字段
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/raw");

            // 设置请求正文 即要提交的数据
            PrintWriter pw = new PrintWriter(new
                    OutputStreamWriter(connection.getOutputStream()));
            pw.print(params);
            pw.flush();
            pw.close();
            // 使用 connect 方法建立到远程对象的实际连接。
            connection.connect();
            InputStream is;
            int code = connection.getResponseCode();
            if (code == 200) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }


            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while (null != (line = br.readLine())) {
                buffer.append(line);
            }
            br.close();
            isr.close();
            is.close();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

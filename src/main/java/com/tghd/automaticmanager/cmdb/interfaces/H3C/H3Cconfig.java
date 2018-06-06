package com.tghd.automaticmanager.cmdb.interfaces.H3C;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class H3Cconfig {
    @Value("${H3C.host}")
    private String host;
    @Value("${H3C.port}")
    private Integer port;
    @Value("${H3C.username}")
    private String username;
    @Value("${H3C.password}")
    private String password;
    @Value("${H3C.realm}")
    private String realm;
    @Value("${H3C.connectionTimeout}")
    private Integer connectionTimeout;
    @Value("${H3C.soTimeout}")
    private Integer soTimeout;
    @Value("${H3C.sendBufferSize}")
    private Integer sendBufferSize;
    @Value("${H3C.seceiveBufferSize}")
    private Integer receiveBufferSize;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(Integer soTimeout) {
        this.soTimeout = soTimeout;
    }

    public Integer getSendBufferSize() {
        return sendBufferSize;
    }

    public void setSendBufferSize(Integer sendBufferSize) {
        this.sendBufferSize = sendBufferSize;
    }

    public Integer getReceiveBufferSize() {
        return receiveBufferSize;
    }

    public void setReceiveBufferSize(Integer receiveBufferSize) {
        this.receiveBufferSize = receiveBufferSize;
    }
}

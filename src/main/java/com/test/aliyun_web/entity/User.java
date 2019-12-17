package com.test.aliyun_web.entity;

public class User {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ip='" + ip + '\'' +
                ", fileDownloadCount=" + fileDownloadCount +
                ", roles='" + roles + '\'' +
                '}';
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getFileDownloadCount() {
        return fileDownloadCount;
    }

    public void setFileDownloadCount(Integer fileDownloadCount) {
        this.fileDownloadCount = fileDownloadCount;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    private String username;
    private String password;
    private String ip;
    private Integer fileDownloadCount;
    private String roles;
}

package cn.huan.t_store.entity;

import java.util.Date;

/**
 * @ProjectName: t_store 
 * @ClassName: Log
 * @Author: YYH
 * @Description: xxx  
 * @CreateDate: 2021/5/8 11:54
 */
public class Log {
    private Integer id;
    private String username;
    private String url;
    private Integer num;
    private Date times;
    private Integer pid;

    public Log() {
    }

    public Log(String username, String url, Date times) {
        this.username = username;
        this.url = url;
        this.times = times;
    }


    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", url='" + url + '\'' +
                ", num=" + num +
                ", times=" + times +
                ", productId=" + pid +
                '}';
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }
}

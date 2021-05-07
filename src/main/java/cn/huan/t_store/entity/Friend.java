package cn.huan.t_store.entity;

/**
 * @ProjectName: t_store 
 * @ClassName: Friend
 * @Author: YYH
 * @Description: xxx  
 * @CreateDate: 2021/5/7 11:15
 */
public class Friend {
    Integer id;
    Integer uid;
    String friendname;
    String friendship;

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", uid=" + uid +
                ", friendname='" + friendname + '\'' +
                ", friendship='" + friendship + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFriendname() {
        return friendname;
    }

    public void setFriendname(String friendname) {
        this.friendname = friendname;
    }

    public String getFriendship() {
        return friendship;
    }

    public void setFriendship(String friendship) {
        this.friendship = friendship;
    }
}

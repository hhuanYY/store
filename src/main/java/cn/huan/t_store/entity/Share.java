package cn.huan.t_store.entity;

import java.util.Date;

/**
 * @program: t_store
 * @description: 分享实体
 * @author: 袁银欢
 * @createTime: 2021/02/02 18:16
 **/
public class Share extends BaseEntity {
    /**
     * 分享id
     */
    private Integer id;

    /**
     * 商品id
     */
    private Integer proid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 分享者
     */
    private String sharer;

    /**
     * 分享时间
     * @return
     */
    private Date sharetime;


    public Date getSharetime() {
        return sharetime;
    }

    public void setSharetime(Date sharetime) {
        this.sharetime = sharetime;
    }

    public String getSharer() {
        return sharer;
    }

    public void setSharer(String sharer) {
        this.sharer = sharer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}

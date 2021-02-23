package cn.huan.t_store.entity;

import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * @program: t_store
 * @description: 购买实体
 * @author: 袁银欢
 * @createTime: 2021/02/08 14:56
 **/
public class BuyNow extends Serializers.Base {
    /**
     * '购物车货物id'
     */
    private Integer cid;

    /**
     * '用户id'
     */
    private Integer uid;

    /**
     * '商品id'
     */
    private Integer pid;

    /**
     * '商品数量'
     */
    private Integer num;

    /**
     * '加入购物车时的价格'
     */
    private Integer price;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

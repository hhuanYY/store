package cn.huan.t_store.entity;

import java.util.Date;

/**
 * @program: t_store
 * @description: 封装分享查询结果
 * @author: 袁银欢
 * @createTime: 2021/02/04 16:16
 **/
public class ShareVO extends BaseEntity {
    /**
     * 商品信息
     */
    private Long price;
    private String title;
    private String image;

    /**
     * 分享人
     */
    private String sharer;

    /**
     * 分享时间
     */
    private String sharetime;

    public String getSharetime() {
        return sharetime;
    }

    public void setSharetime(String sharetime) {
        this.sharetime = sharetime;
    }

    @Override
    public String toString() {
        return "ShareVO{" +
                "price=" + price +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", sharer='" + sharer + '\'' +
                ", sharetime=" + sharetime +
                '}';
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSharer() {
        return sharer;
    }

    public void setSharer(String sharer) {
        this.sharer = sharer;
    }
}

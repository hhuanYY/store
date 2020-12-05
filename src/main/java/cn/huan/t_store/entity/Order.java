package cn.huan.t_store.entity;

import java.util.Date;

/**
 * 订单实体类数据表
 * 
 * @author Administrator
 *
 */
public class Order extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3939386310124141002L;


	private Integer oid; // '订单id',
	private Integer uid; // '用户id',
	private String recvName; // '收货人姓名',
	private String recvPhone; // '收货人电话',
	private String recvProvince; // '收货省',
	private String recvCity; // '收货市',
	private String recvArea; // '收货区',
	private String recvAddress; // '收货详细地址',
	private Long payAmount; // '支付金额',
	private Integer status; // '订单状态：0-未支付，1-已支付，2-已取消，3-已关闭',
	private Date orderTime; // '下单时间',
	private Date payTime; // '支付时间',

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getRecvName() {
		return recvName;
	}

	public void setRecvName(String recvName) {
		this.recvName = recvName;
	}

	public String getRecvPhone() {
		return recvPhone;
	}

	public void setRecvPhone(String recvPhone) {
		this.recvPhone = recvPhone;
	}

	public String getRecvProvince() {
		return recvProvince;
	}

	public void setRecvProvince(String recvProvince) {
		this.recvProvince = recvProvince;
	}

	public String getRecvCity() {
		return recvCity;
	}

	public void setRecvCity(String recvCity) {
		this.recvCity = recvCity;
	}

	public String getRecvArea() {
		return recvArea;
	}

	public void setRecvArea(String recvArea) {
		this.recvArea = recvArea;
	}

	public String getRecvAddress() {
		return recvAddress;
	}

	public void setRecvAddress(String recvAddress) {
		this.recvAddress = recvAddress;
	}

	public Long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Long payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", recvName=" + recvName + ", recvPhone=" + recvPhone
				+ ", recvProvince=" + recvProvince + ", recvCity=" + recvCity + ", recvArea=" + recvArea
				+ ", recvAddress=" + recvAddress + ", payAmount=" + payAmount + ", status=" + status + ", orderTime="
				+ orderTime + ", payTime=" + payTime + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		return true;
	}

}

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券领取记录表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-24 13:44:22
 */
@Data
@TableName("xh_coupon_receive")
public class XhCouponReceiveEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自动增加ID
	 */
	@TableId
	private Long id;
	/**
	 * 买家ID
	 */
	private Long buyerId;
	/**
	 * 优惠券编号
	 */
	private Long couponId;
	/**
	 * 券额
	 */
	private BigDecimal couponMoney;
	/**
	 * 领取时间
	 */
	private Date createTime;
	/**
	 * 金额满
	 */
	private BigDecimal fullMoney;
	/**
	 * 分享者ID
	 */
	private Long shareId;
	/**
	 * 状态，1为已使用，0为已领取未使用，-1为已过期
	 */
	private Integer status;


	/**
	 * 门店名称
	 */
	@TableField(exist=false)
	private String buyerName;
	/**
	 * 类别名称
	 */
	@TableField(exist=false)
	private String shareName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public BigDecimal getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(BigDecimal couponMoney) {
		this.couponMoney = couponMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getFullMoney() {
		return fullMoney;
	}

	public void setFullMoney(BigDecimal fullMoney) {
		this.fullMoney = fullMoney;
	}

	public Long getShareId() {
		return shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
}

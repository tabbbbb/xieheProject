package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券消费记录表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-24 13:44:22
 */
@Data
@TableName("xh_coupon_logs")
public class XhCouponLogsEntity implements Serializable {
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
	 * 分享ID
	 */
	private Long shareId;
	/**
	 * 优惠券id
	 */
	private Long couponReceiveId;
	/**
	 * 订单号
	 */
	private String orderNumber;
	/**
	 * 原订单金额
	 */
	private BigDecimal orderOriginalAmount;
	/**
	 * 优惠券的金额
	 */
	private BigDecimal couponAmount;
	/**
	 * 抵扣优惠券之后的订单金额
	 */
	private BigDecimal orderFinalAmount;
	/**
	 * 领取时间
	 */
	private Date createTime;
	/**
	 * 日志状态: 默认为0，支付回调成功后为1
	 */
	private Integer status;


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

	public Long getShareId() {
		return shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	public Long getCouponReceiveId() {
		return couponReceiveId;
	}

	public void setCouponReceiveId(Long couponReceiveId) {
		this.couponReceiveId = couponReceiveId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BigDecimal getOrderOriginalAmount() {
		return orderOriginalAmount;
	}

	public void setOrderOriginalAmount(BigDecimal orderOriginalAmount) {
		this.orderOriginalAmount = orderOriginalAmount;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getOrderFinalAmount() {
		return orderFinalAmount;
	}

	public void setOrderFinalAmount(BigDecimal orderFinalAmount) {
		this.orderFinalAmount = orderFinalAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

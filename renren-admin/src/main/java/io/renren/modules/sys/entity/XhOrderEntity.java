package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-10 16:25:22
 */
@Data
@TableName("xh_order")
public class XhOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	@TableId
	private Integer id;
	/**
	 * 订单编号
	 */
	private String orderId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 支付方式
	 */
	private String pid;
	/**
	 * 订单时间
	 */
	private Date orderTime;
	/**
	 * 总价
	 */
	private BigDecimal totalPrices;
	/**
	 * 订单状态
	 */
	private Integer status;

	/**
	 * 商品id
	 */
	private Integer goodsId;
	/**
	 * 实际支付费用
	 */
	private BigDecimal payPrice;
	/**
	 * 核销时间
	 */
	private Date useTime;
	/**
	 * 核销员
	 */
	private Integer clerkId;
	/**
	 * 门店id
	 */
	private Integer shopId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public BigDecimal getTotalPrices() {
		return totalPrices;
	}

	public void setTotalPrices(BigDecimal totalPrices) {
		this.totalPrices = totalPrices;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public Integer getClerkId() {
		return clerkId;
	}

	public void setClerkId(Integer clerkId) {
		this.clerkId = clerkId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
}

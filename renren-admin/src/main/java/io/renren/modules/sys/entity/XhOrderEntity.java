package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
	private Integer deptId;
	/**
	 * 优惠券编号
	 */
	private long couponId;
	/**
	 * 优惠券优惠金额
	 */
	private BigDecimal couponPrice;
	/**
	 * 订单积分
	 */
	private Integer point;
	/**
	 * 商品数量
	 */
	private Integer goodsCount;
	/**
	 * 门店名称
	 */
	private Integer deleteMark;
	@TableField(exist=false)
	private String deptName;
	/**
	 * 大图
	 */
	@TableField(exist=false)
	private String picture;
	/**
	 * 缩略图
	 */
	@TableField(exist=false)
	private String coverPic;
	/**
	 * 商品名称
	 */
	@TableField(exist=false)
	private String goodsName;
	/**
	 * 用户名称
	 */
	@TableField(exist=false)
	private String userName;


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


	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}


	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}

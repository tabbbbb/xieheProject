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

}

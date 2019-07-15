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

}

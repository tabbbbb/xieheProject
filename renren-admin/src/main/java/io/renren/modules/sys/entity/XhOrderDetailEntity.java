package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
@TableName("xh_order_detail")
public class XhOrderDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单明细id
	 */
	@TableId
	private Integer id;
	/**
	 * 订单编号
	 */
	private String orderId;
	/**
	 * 订单明细编号
	 */
	private String orderDetailId;
	/**
	 * 商品编号
	 */
	private Integer goodsId;
	/**
	 * 数量
	 */
	private Integer count;

}

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
 * @date 2019-07-10 16:25:21
 */
@Data
@TableName("xh_goods")
public class XhGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId
	private Integer id;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品标题
	 */
	private String goodsTitle;
	/**
	 * 商品介绍
	 */
	private String goodsAbstract;
	/**
	 * 单价
	 */
	private BigDecimal goodsPrice;
	/**
	 * 销量
	 */
	private String goodsSell;

}

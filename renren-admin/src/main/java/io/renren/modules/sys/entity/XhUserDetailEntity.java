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
 * @date 2019-07-10 16:25:21
 */
@Data
@TableName("xh_user_detail")
public class XhUserDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户明细id
	 */
	@TableId
	private Integer id;
	/**
	 * 用户编号
	 */
	private Integer userId;
	/**
	 * 用户性别
	 */
	private Integer userGender;
	/**
	 * 昵称
	 */
	private String nikName;
	/**
	 * 余额
	 */
	private Integer money;
	/**
	 * 积分
	 */
	private Integer point;
	/**
	 * 会员等级
	 */
	private Integer level;

}

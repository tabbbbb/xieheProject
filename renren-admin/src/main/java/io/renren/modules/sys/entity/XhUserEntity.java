package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @date 2019-07-27 09:25:43
 */
@Data
@TableName("xh_user")
public class XhUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * wx用户id
	 */
	@TableId
	private Integer id;
	/**
	 * 微信open_id
	 */
	private String openId;
	/**
	 * 昵称
	 */
	private String nikName;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 用户类型：0、用户 1、销售
	 */
	private Integer userType;
	/**
	 * 销售编号
	 */
	private Integer sellId;
	/**
	 * 公司编号
	 */
	private Integer shopId;
	/**
	 * 会员编号
	 */
	private Integer parentId;
	/**
	 * 积分
	 */
	private Integer point;
	/**
	 * 会员等级
	 */
	private Integer level;
	/**
	 * 电话号码
	 */
	private String phone;
	/**
	 * 二维码
	 */
	private String qrcode;


	/**
	 * 门店名称
	 */
	@TableField(exist=false)
	private String shopName;
	/**
	 * 销售名称
	 */
	@TableField(exist=false)
	private String sellName;
	/**
	 * 下级会员名称
	 */
	@TableField(exist=false)
	private String parentName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNikName() {
		return nikName;
	}

	public void setNikName(String nikName) {
		this.nikName = nikName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getSellId() {
		return sellId;
	}

	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
}

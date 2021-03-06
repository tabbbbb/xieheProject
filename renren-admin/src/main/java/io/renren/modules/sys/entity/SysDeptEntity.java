/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_dept")
@ApiModel(value = "商户实体")
public class SysDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID")
	@TableId
	private Long deptId;
	/**
	 * 上级部门ID，一级部门为0
	 */
	@ApiModelProperty(value = "上级部门ID")
	private Long parentId;
	/**
	 * 部门名称
	 */
	@ApiModelProperty(value = "部门名称")
	private String name;
	/**
	 * 经度
	 */
	@ApiModelProperty(value = "经度")
	private String longitude;
	/**
	 * 纬度
	 */
	@ApiModelProperty(value = "纬度")
	private String latitude;
	/**
	 * 门店积分
	 */
	@ApiModelProperty(value = "门店积分")
	private int shopPoint;
	/**
	 * 门店地址
	 */
	@ApiModelProperty(value = "门店地址")
	private String address;
	/**
	 * 门店图片
	 */
	@ApiModelProperty(value = "门店图片")
	private String shopPic;
	/**
	 * 门店电话
	 */
	@ApiModelProperty(value = "门店电话")
	private String telephone;
	/**
	 * 上级部门名称
	 */
	@ApiModelProperty(value = "上级部门名称")
	@TableField(exist=false)
	private String parentName;
	/**
	 * 距离
	 */
	@ApiModelProperty(value = "上级部门名称")
	@TableField(exist=false)
	private double distance;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer orderNum;
	@TableLogic
	private Integer delFlag;
	/**
	 * ztree属性
	 */
	@TableField(exist=false)
	private Boolean open;
	@TableField(exist=false)
	private List<?> list;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getShopPoint() {
		return shopPoint;
	}

	public void setShopPoint(int shopPoint) {
		this.shopPoint = shopPoint;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getShopPic() {
		return shopPic;
	}

	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
}

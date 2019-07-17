package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
	private String name;
	/**
	 * 商品标题
	 */
	private String title;
	/**
	 * 商品介绍
	 */
	private String  detail;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 销量
	 */
	private Integer sales;
	/**
	 * 原价
	 */
	private BigDecimal originalPrice;
	/**
	 * 商品分类
	 */
	private Integer catId;
	/**
	 * 商品状态，1上架，2下架
	 */
	private Integer status;
	/**
	 * 商品排序
	 */
	private Integer sort;
	/**
	 * 商品缩略图
	 */
	private String coverPic;
	/**
	 * 商品图
	 */
	private String picture;
	/**
	 * 添加时间
	 */
	private Date addTime;
	/**
	 * 删除标记
	 */
	@TableLogic
	private Integer isDelete;
	/**
	 * 门店ID
	 */
	private Integer deptId;


	/**
	 * 门店名称
	 */
	@TableField(exist=false)
	private String deptName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal original_price) {
		this.originalPrice = original_price;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}


	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}

package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.XhCouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券基础配置表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-24 13:44:22
 */
@Mapper
public interface XhCouponDao extends BaseMapper<XhCouponEntity> {
	
}

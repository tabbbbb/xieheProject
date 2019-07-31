package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.XhCouponLogsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券消费记录表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-24 13:44:22
 */
@Mapper
public interface XhCouponLogsDao extends BaseMapper<XhCouponLogsEntity> {
	
}

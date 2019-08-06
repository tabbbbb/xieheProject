package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.sys.entity.XhCouponReceiveEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.XhGoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券领取记录表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-24 13:44:22
 */
@Mapper
public interface XhCouponReceiveDao extends BaseMapper<XhCouponReceiveEntity> {

    List<XhCouponReceiveEntity> queryCoupon(XhCouponReceiveEntity xhCouponReceiveEntity);

    IPage<XhCouponReceiveEntity> findByPage(IPage<XhCouponReceiveEntity> page, @Param("ew") QueryWrapper<XhCouponReceiveEntity> xhCouponReceiveEntityQueryWrapper);
}

package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.XhCouponEntity;

import java.util.Map;

/**
 * 优惠券基础配置表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-24 13:44:22
 */
public interface XhCouponService extends IService<XhCouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


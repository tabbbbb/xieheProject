package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.XhOrderEntity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-10 16:25:22
 */
public interface XhOrderService extends IService<XhOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
    Object unifiedOrder(String outTradeNo, BigDecimal money, String openid) throws Exception;
}


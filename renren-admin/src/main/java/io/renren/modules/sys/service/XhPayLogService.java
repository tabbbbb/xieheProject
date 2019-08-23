package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.XhPayLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-08-14 09:51:12
 */
public interface XhPayLogService extends IService<XhPayLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


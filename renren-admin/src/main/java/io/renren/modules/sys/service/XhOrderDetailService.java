package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.XhOrderDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-10 16:25:22
 */
public interface XhOrderDetailService extends IService<XhOrderDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


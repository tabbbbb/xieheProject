package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.XhUserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-27 09:25:43
 */
public interface XhUserService extends IService<XhUserEntity> {

    PageUtils queryPage(Map<String, Object> params) throws Exception;
    PageUtils checkUser(Map<String, Object> params) throws Exception;

    List<XhUserEntity> querySellList(Map<String, Object> map);
}


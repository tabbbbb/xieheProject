package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.XhPayLogDao;
import io.renren.modules.sys.entity.XhPayLogEntity;
import io.renren.modules.sys.service.XhPayLogService;


@Service("xhPayLogService")
public class XhPayLogServiceImpl extends ServiceImpl<XhPayLogDao, XhPayLogEntity> implements XhPayLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<XhPayLogEntity> page = this.page(
                new Query<XhPayLogEntity>().getPage(params),
                new QueryWrapper<XhPayLogEntity>()
        );

        return new PageUtils(page);
    }

}

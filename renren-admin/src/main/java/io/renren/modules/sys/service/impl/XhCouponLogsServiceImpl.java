package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.XhCouponLogsDao;
import io.renren.modules.sys.entity.XhCouponLogsEntity;
import io.renren.modules.sys.service.XhCouponLogsService;


@Service("xhCouponLogsService")
public class XhCouponLogsServiceImpl extends ServiceImpl<XhCouponLogsDao, XhCouponLogsEntity> implements XhCouponLogsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<XhCouponLogsEntity> page = this.page(
                new Query<XhCouponLogsEntity>().getPage(params),
                new QueryWrapper<XhCouponLogsEntity>()
        );

        return new PageUtils(page);
    }

}

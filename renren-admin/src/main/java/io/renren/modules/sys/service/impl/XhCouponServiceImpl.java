package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.XhCouponDao;
import io.renren.modules.sys.entity.XhCouponEntity;
import io.renren.modules.sys.service.XhCouponService;


@Service("xhCouponService")
public class XhCouponServiceImpl extends ServiceImpl<XhCouponDao, XhCouponEntity> implements XhCouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String date = (String)params.get("date");
        IPage<XhCouponEntity> page = this.page(
                new Query<XhCouponEntity>().getPage(params),
                new QueryWrapper<XhCouponEntity>()
                .ge(StringUtils.isNotBlank(date),"end_time",date)
                .le(StringUtils.isNotBlank(date),"start_time",date)

        );

        return new PageUtils(page);
    }

}

package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.XhCouponReceiveDao;
import io.renren.modules.sys.entity.XhCouponReceiveEntity;
import io.renren.modules.sys.service.XhCouponReceiveService;


@Service("xhCouponReceiveService")
public class XhCouponReceiveServiceImpl extends ServiceImpl<XhCouponReceiveDao, XhCouponReceiveEntity> implements XhCouponReceiveService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String status = (String) params.get("status");
        String id = (String)params.get("id");
        IPage<XhCouponReceiveEntity> page = baseMapper.findByPage(
                new Query<XhCouponReceiveEntity>().getPage(params),
                new QueryWrapper<XhCouponReceiveEntity>()
                .like(StringUtils.isNotBlank(status),"a.status", status)
                .like(StringUtils.isNotBlank(id),"a.buyer_id", id)
        );

        return new PageUtils(page);
    }
    @Override
    public boolean checkCoupon(XhCouponReceiveEntity xhCouponReceive){
        long CouponId = xhCouponReceive.getCouponId();
        List<XhCouponReceiveEntity> xhCouponReceiveCheck = baseMapper.queryCoupon(CouponId);
        return xhCouponReceiveCheck.size()==0;
    }

}

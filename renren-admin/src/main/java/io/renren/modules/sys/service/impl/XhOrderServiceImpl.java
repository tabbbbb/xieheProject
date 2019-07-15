package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.XhOrderDao;
import io.renren.modules.sys.entity.XhOrderEntity;
import io.renren.modules.sys.service.XhOrderService;


@Service("xhOrderService")
public class XhOrderServiceImpl extends ServiceImpl<XhOrderDao, XhOrderEntity> implements XhOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<XhOrderEntity> page = this.page(
                new Query<XhOrderEntity>().getPage(params),
                new QueryWrapper<XhOrderEntity>()
        );

        return new PageUtils(page);
    }

}

package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.XhOrderDetailDao;
import io.renren.modules.sys.entity.XhOrderDetailEntity;
import io.renren.modules.sys.service.XhOrderDetailService;


@Service("xhOrderDetailService")
public class XhOrderDetailServiceImpl extends ServiceImpl<XhOrderDetailDao, XhOrderDetailEntity> implements XhOrderDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<XhOrderDetailEntity> page = this.page(
                new Query<XhOrderDetailEntity>().getPage(params),
                new QueryWrapper<XhOrderDetailEntity>()
        );

        return new PageUtils(page);
    }

}

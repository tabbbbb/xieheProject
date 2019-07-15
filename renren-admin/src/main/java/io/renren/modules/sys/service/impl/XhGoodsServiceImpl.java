package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.XhGoodsDao;
import io.renren.modules.sys.entity.XhGoodsEntity;
import io.renren.modules.sys.service.XhGoodsService;


@Service("xhGoodsService")
public class XhGoodsServiceImpl extends ServiceImpl<XhGoodsDao, XhGoodsEntity> implements XhGoodsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<XhGoodsEntity> page = this.page(
                new Query<XhGoodsEntity>().getPage(params),
                new QueryWrapper<XhGoodsEntity>()
        );

        return new PageUtils(page);
    }

}

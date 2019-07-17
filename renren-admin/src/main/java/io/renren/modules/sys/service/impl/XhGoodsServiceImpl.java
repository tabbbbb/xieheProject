package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.entity.SysDeptEntity;
import io.renren.modules.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private SysDeptService sysDeptService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<XhGoodsEntity> page = this.page(
                new Query<XhGoodsEntity>().getPage(params),
                new QueryWrapper<XhGoodsEntity>()
        );
        for(XhGoodsEntity xhGoodsEntity : page.getRecords()){
            SysDeptEntity sysDeptEntity = sysDeptService.getById(xhGoodsEntity.getDeptId());
            xhGoodsEntity.setDeptName(sysDeptEntity.getName());
        }
        return new PageUtils(page);
    }

}

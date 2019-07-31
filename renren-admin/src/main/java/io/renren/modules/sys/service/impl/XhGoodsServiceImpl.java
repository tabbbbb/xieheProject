package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.entity.SysDeptEntity;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDeptService;
import io.renren.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
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

import javax.annotation.Resource;


@Service("xhGoodsService")
public class XhGoodsServiceImpl extends ServiceImpl<XhGoodsDao, XhGoodsEntity> implements XhGoodsService {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysDictService sysDictService;
    @Resource
    private XhGoodsDao xhGoodsDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) throws Exception {
        String name = (String)params.get("name");
        String deptName = (String)params.get("deptName");
        String dictName = (String)params.get("dictName");
        String status = (String)params.get("status");
        IPage<XhGoodsEntity> page = xhGoodsDao.findByPage(
                new Query<XhGoodsEntity>().getPage(params),
                new QueryWrapper<XhGoodsEntity>()
                .like(StringUtils.isNotBlank(name),"a.name", name)
                .like(StringUtils.isNotBlank(deptName),"b.name", deptName)
                        .like(StringUtils.isNotBlank(dictName),"c.value", dictName)
                .like(StringUtils.isNotBlank(status),"a.status", status)
        );
        return new PageUtils(page);
    }

}

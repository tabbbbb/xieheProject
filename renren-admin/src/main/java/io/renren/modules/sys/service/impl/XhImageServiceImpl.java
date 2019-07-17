package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.XhImageDao;
import io.renren.modules.sys.entity.XhImageEntity;
import io.renren.modules.sys.service.XhImageService;

import javax.annotation.Resource;


@Service("xhImageService")
public class XhImageServiceImpl extends ServiceImpl<XhImageDao, XhImageEntity> implements XhImageService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private XhImageDao xhImageDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) throws Exception {
        String name = (String)params.get("name");
        IPage<XhImageEntity> page = this.xhImageDao.findByPage(
                new Query<XhImageEntity>().getPage(params),
                new QueryWrapper<XhImageEntity>()
                        .like(StringUtils.isNotBlank(name),"b.name", name)
        );
        return new PageUtils(page);
    }
}

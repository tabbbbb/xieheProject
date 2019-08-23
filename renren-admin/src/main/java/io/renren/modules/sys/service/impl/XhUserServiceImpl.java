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

import io.renren.modules.sys.dao.XhUserDao;
import io.renren.modules.sys.entity.XhUserEntity;
import io.renren.modules.sys.service.XhUserService;

import javax.annotation.Resource;


@Service("xhUserService")
public class XhUserServiceImpl extends ServiceImpl<XhUserDao, XhUserEntity> implements XhUserService {
    @Resource
    private XhUserDao xhUserDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) throws Exception{
        String phone =(String)params.get("phone");
        String shopName = (String)params.get("shopName");
        IPage<XhUserEntity> page = xhUserDao.findByPage(
                new Query<XhUserEntity>().getPage(params),
                new QueryWrapper<XhUserEntity>()

                        .like(StringUtils.isNotBlank(phone),"a.phone", phone)
                        .like(StringUtils.isNotBlank(shopName),"b.name", shopName)
        );
        return new PageUtils(page);
    }
    @Override
    public XhUserEntity checkUser(Map<String, Object> params) throws Exception{
        String openId = (String)params.get("openId");
        XhUserEntity xhUserEntity  = xhUserDao.checkUser(openId);
        return xhUserEntity;
    }
    @Override
    public List<XhUserEntity> querySellList(Map<String, Object> params){
        return xhUserDao.querySellList(params);
    }
}

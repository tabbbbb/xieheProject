package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.sys.entity.XhOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-10 16:25:22
 */
@Mapper
public interface XhOrderDao extends BaseMapper<XhOrderEntity> {

    XhOrderEntity findByOrderNo(String orderNo);

    IPage<XhOrderEntity> findByPage(IPage<XhOrderEntity> page,@Param("ew") QueryWrapper<XhOrderEntity> xhOrderEntityQueryWrapper);
}

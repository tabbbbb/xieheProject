package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.sys.entity.XhUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-27 09:25:43
 */
@Mapper
public interface XhUserDao extends BaseMapper<XhUserEntity> {
    IPage<XhUserEntity> findByPage(IPage<XhUserEntity> page, @Param("ew") QueryWrapper<XhUserEntity> xhGoodsEntityQueryWrapper) throws Exception;

    List<XhUserEntity> querySellList(Map<String, Object> params);
}

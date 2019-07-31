package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.sys.entity.XhGoodsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-10 16:25:21
 */
@Mapper
public interface XhGoodsDao extends BaseMapper<XhGoodsEntity> {


    IPage<XhGoodsEntity> findByPage(IPage<XhGoodsEntity> page,@Param("ew") QueryWrapper<XhGoodsEntity> xhGoodsEntityQueryWrapper) throws Exception;
}

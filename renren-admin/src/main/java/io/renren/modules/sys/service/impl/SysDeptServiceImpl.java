/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.annotation.DataFilter;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.PointToDistance;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysDeptDao;
import io.renren.modules.sys.entity.SysDeptEntity;
import io.renren.modules.sys.service.SysDeptService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {
	@Resource
	private SysDeptDao deptDao;
	@Override
	@DataFilter(subDept = true, user = false, tableAlias = "t1")
	public List<SysDeptEntity> queryList(Map<String, Object> params){
		return baseMapper.queryList(params);
	}

	@Override
	@DataFilter(subDept = true, user = false, tableAlias = "t1")
	public List<SysDeptEntity> queryParentList(Map<String, Object> params){
		return deptDao.queryParentList(params);
	}

	@Override
	public List<Long> queryDetpIdList(Long parentId) {
		return baseMapper.queryDetpIdList(parentId);
	}

	@Override
	public List<Long> getSubDeptIdList(Long deptId){
		//部门及子部门ID列表
		List<Long> deptIdList = new ArrayList<>();

		//获取子部门ID
		List<Long> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);

		return deptIdList;
	}

	/**
	 * 递归
	 */
	private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
		for(Long deptId : subIdList){
			List<Long> list = queryDetpIdList(deptId);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}

			deptIdList.add(deptId);
		}
	}


	@Override
	public PageUtils apiQueryPage(Map<String, Object> params) throws Exception {
		double userLat = Double.parseDouble(params.get("userLat").toString());
		double userLng = Double.parseDouble(params.get("userLng").toString());
		IPage<SysDeptEntity> page = this.deptDao.findApiPage(
				new Query<SysDeptEntity>().getPage(params),
				new QueryWrapper<SysDeptEntity>()
		);
		for (SysDeptEntity sysDeptEntity:page.getRecords()){
			double distance = PointToDistance.getDistanceFromTwoPoints(userLat,userLng,Double.parseDouble(sysDeptEntity.getLatitude()),Double.parseDouble(sysDeptEntity.getLongitude()));
			sysDeptEntity.setDistance(distance);
		}
		return new PageUtils(page);
	}

}

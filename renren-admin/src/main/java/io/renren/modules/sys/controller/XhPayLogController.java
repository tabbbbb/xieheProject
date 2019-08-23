package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.XhPayLogEntity;
import io.renren.modules.sys.service.XhPayLogService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-08-14 09:51:12
 */
@RestController
@RequestMapping("sys/xhpaylog")
public class XhPayLogController {
    @Autowired
    private XhPayLogService xhPayLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:xhpaylog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xhPayLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:xhpaylog:info")
    public R info(@PathVariable("id") Integer id){
        XhPayLogEntity xhPayLog = xhPayLogService.getById(id);

        return R.ok().put("xhPayLog", xhPayLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:xhpaylog:save")
    public R save(@RequestBody XhPayLogEntity xhPayLog){
        xhPayLogService.save(xhPayLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:xhpaylog:update")
    public R update(@RequestBody XhPayLogEntity xhPayLog){
        ValidatorUtils.validateEntity(xhPayLog);
        xhPayLogService.updateById(xhPayLog);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:xhpaylog:delete")
    public R delete(@RequestBody Integer[] ids){
        xhPayLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

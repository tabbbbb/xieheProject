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

import io.renren.modules.sys.entity.XhCouponLogsEntity;
import io.renren.modules.sys.service.XhCouponLogsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 优惠券消费记录表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-24 13:44:22
 */
@RestController
@RequestMapping("sys/xhcouponlogs")
public class XhCouponLogsController {
    @Autowired
    private XhCouponLogsService xhCouponLogsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:xhcouponlogs:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xhCouponLogsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:xhcouponlogs:info")
    public R info(@PathVariable("id") Long id){
        XhCouponLogsEntity xhCouponLogs = xhCouponLogsService.getById(id);

        return R.ok().put("xhCouponLogs", xhCouponLogs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:xhcouponlogs:save")
    public R save(@RequestBody XhCouponLogsEntity xhCouponLogs){
        xhCouponLogsService.save(xhCouponLogs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:xhcouponlogs:update")
    public R update(@RequestBody XhCouponLogsEntity xhCouponLogs){
        ValidatorUtils.validateEntity(xhCouponLogs);
        xhCouponLogsService.updateById(xhCouponLogs);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:xhcouponlogs:delete")
    public R delete(@RequestBody Long[] ids){
        xhCouponLogsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

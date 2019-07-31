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

import io.renren.modules.sys.entity.XhCouponReceiveEntity;
import io.renren.modules.sys.service.XhCouponReceiveService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 优惠券领取记录表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-24 13:44:22
 */
@RestController
@RequestMapping("sys/xhcouponreceive")
public class XhCouponReceiveController {
    @Autowired
    private XhCouponReceiveService xhCouponReceiveService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:xhcouponreceive:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xhCouponReceiveService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:xhcouponreceive:info")
    public R info(@PathVariable("id") Long id){
        XhCouponReceiveEntity xhCouponReceive = xhCouponReceiveService.getById(id);

        return R.ok().put("xhCouponReceive", xhCouponReceive);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:xhcouponreceive:save")
    public R save(@RequestBody XhCouponReceiveEntity xhCouponReceive){
        xhCouponReceiveService.save(xhCouponReceive);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:xhcouponreceive:update")
    public R update(@RequestBody XhCouponReceiveEntity xhCouponReceive){
        ValidatorUtils.validateEntity(xhCouponReceive);
        xhCouponReceiveService.updateById(xhCouponReceive);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:xhcouponreceive:delete")
    public R delete(@RequestBody Long[] ids){
        xhCouponReceiveService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}

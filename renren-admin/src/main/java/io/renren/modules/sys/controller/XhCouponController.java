package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.XhCouponEntity;
import io.renren.modules.sys.service.XhCouponService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 优惠券基础配置表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-24 13:44:22
 */
@RestController
@RequestMapping("sys/xhcoupon")
public class XhCouponController {
    @Autowired
    private XhCouponService xhCouponService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:xhcoupon:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xhCouponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:xhcoupon:info")
    public R info(@PathVariable("id") Long id){
        XhCouponEntity xhCoupon = xhCouponService.getById(id);

        return R.ok().put("xhCoupon", xhCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:xhcoupon:save")
    public R save(@RequestBody XhCouponEntity xhCoupon){
        Date date = new Date();
        if(isEffectiveDate(date,xhCoupon.getStartTime(),xhCoupon.getEndTime())){
            xhCoupon.setStatus(1);
        }else if(xhCoupon.getStartTime().getTime()-date.getTime()>0){
            xhCoupon.setStatus(0);
        }else if(xhCoupon.getEndTime().getTime()-date.getTime()<0){
            xhCoupon.setStatus(-1);
        }
        xhCoupon.setCreateTime(date);
        xhCouponService.save(xhCoupon);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:xhcoupon:update")
    public R update(@RequestBody XhCouponEntity xhCoupon){
        ValidatorUtils.validateEntity(xhCoupon);
        xhCouponService.updateById(xhCoupon);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:xhcoupon:delete")
    public R delete(@RequestBody Long[] ids){
        xhCouponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}

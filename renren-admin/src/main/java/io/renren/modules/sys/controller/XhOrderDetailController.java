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

import io.renren.modules.sys.entity.XhOrderDetailEntity;
import io.renren.modules.sys.service.XhOrderDetailService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-10 16:25:22
 */
@RestController
@RequestMapping("sys/xhorderdetail")
public class XhOrderDetailController {
    @Autowired
    private XhOrderDetailService xhOrderDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:xhorderdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xhOrderDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:xhorderdetail:info")
    public R info(@PathVariable("id") Integer id){
        XhOrderDetailEntity xhOrderDetail = xhOrderDetailService.getById(id);

        return R.ok().put("xhOrderDetail", xhOrderDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:xhorderdetail:save")
    public R save(@RequestBody XhOrderDetailEntity xhOrderDetail){
        xhOrderDetailService.save(xhOrderDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:xhorderdetail:update")
    public R update(@RequestBody XhOrderDetailEntity xhOrderDetail){
        ValidatorUtils.validateEntity(xhOrderDetail);
        xhOrderDetailService.updateById(xhOrderDetail);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:xhorderdetail:delete")
    public R delete(@RequestBody Integer[] ids){
        xhOrderDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

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

import io.renren.modules.sys.entity.XhOrderEntity;
import io.renren.modules.sys.service.XhOrderService;
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
@RequestMapping("sys/xhorder")
public class XhOrderController {
    @Autowired
    private XhOrderService xhOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:xhorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xhOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:xhorder:info")
    public R info(@PathVariable("id") Integer id){
        XhOrderEntity xhOrder = xhOrderService.getById(id);

        return R.ok().put("xhOrder", xhOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:xhorder:save")
    public R save(@RequestBody XhOrderEntity xhOrder){
        xhOrderService.save(xhOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:xhorder:update")
    public R update(@RequestBody XhOrderEntity xhOrder){
        ValidatorUtils.validateEntity(xhOrder);
        xhOrderService.updateById(xhOrder);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:xhorder:delete")
    public R delete(@RequestBody Integer[] ids){
        xhOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.XhUserEntity;
import io.renren.modules.sys.service.XhUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-27 09:25:43
 */
@RestController
@RequestMapping("sys/xhuser")
public class XhUserController {
    @Autowired
    private XhUserService xhUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:xhuser:list")
    public R list(@RequestParam Map<String, Object> params) throws Exception {
        PageUtils page = xhUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:xhuser:info")
    public R info(@PathVariable("id") Integer id){
        XhUserEntity xhUser = xhUserService.getById(id);
        return R.ok().put("xhUser", xhUser);
    }

    @RequestMapping("/ParentList")
    public List<XhUserEntity> ParentList(){
        List<XhUserEntity> sellList = xhUserService.querySellList(new HashMap<String, Object>());
        return sellList;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:xhuser:save")
    public R save(@RequestBody XhUserEntity xhUser){
        xhUserService.save(xhUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:xhuser:update")
    public R update(@RequestBody XhUserEntity xhUser){
        ValidatorUtils.validateEntity(xhUser);
        xhUserService.updateById(xhUser);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:xhuser:delete")
    public R delete(@RequestBody Integer[] ids){
        xhUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

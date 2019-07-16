package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.oss.cloud.OSSFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.XhImageEntity;
import io.renren.modules.sys.service.XhImageService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-10 16:25:21
 */
@RestController
@RequestMapping("sys/xhimage")
public class XhImageController {
    @Autowired
    private XhImageService xhImageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:xhimage:list")
    public R list(@RequestParam Map<String, Object> params) throws Exception{
        PageUtils page = xhImageService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:xhimage:info")
    public R info(@PathVariable("id") Integer id){
        XhImageEntity xhImage = xhImageService.getById(id);

        return R.ok().put("xhImage", xhImage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:xhimage:save")
    public R save(@RequestBody XhImageEntity xhImage){
        ValidatorUtils.validateEntity(xhImage);
        xhImageService.save(xhImage);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:xhimage:update")
    public R update(@RequestBody XhImageEntity xhImage){
        ValidatorUtils.validateEntity(xhImage);
        xhImageService.updateById(xhImage);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:xhimage:delete")
    public R delete(@RequestBody Integer[] ids){
        xhImageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

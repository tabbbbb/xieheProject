package io.renren.modules.sys.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.oss.cloud.OSSFactory;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.XhGoodsEntity;
import io.renren.modules.sys.service.XhGoodsService;
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
@RequestMapping("sys/xhgoods")
public class XhGoodsController {
    @Autowired
    private XhGoodsService xhGoodsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:xhgoods:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xhGoodsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:xhgoods:info")
    public R info(@PathVariable("id") Integer id){
        XhGoodsEntity xhGoods = xhGoodsService.getById(id);

        return R.ok().put("xhGoods", xhGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:xhgoods:save")
    public R save(@RequestBody XhGoodsEntity goodsEntity){
        //校验类型
        ValidatorUtils.validateEntity(goodsEntity);
        xhGoodsService.save(goodsEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:xhgoods:update")
    public R update(@RequestBody XhGoodsEntity xhGoods){
        ValidatorUtils.validateEntity(xhGoods);
        xhGoodsService.updateById(xhGoods);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:xhgoods:delete")
    public R delete(@RequestBody Integer[] ids){
        xhGoodsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

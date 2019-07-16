package io.renren.modules.api.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.service.XhImageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/")
public class apiController extends AbstractController {
    @Autowired
    private XhImageService xhImageService;
    /**
     * 轮播图列表
     */
    @RequestMapping("/imglist")
    public R list(@RequestParam Map<String, Object> params) throws Exception{
        PageUtils page = xhImageService.queryPage(params);
        return R.ok().put("page", page);
    }
}

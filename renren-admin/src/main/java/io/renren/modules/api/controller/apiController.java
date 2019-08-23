package io.renren.modules.api.controller;

import io.renren.common.utils.*;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.*;
import io.renren.modules.sys.service.*;
import io.swagger.annotations.*;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@Api("APP数据访问接口")
public class apiController extends AbstractController {
    @Autowired
    private XhImageService xhImageService;
    @Autowired
    private SysDeptService deptService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private XhGoodsService xhGoodsService;
    @Autowired
    private XhOrderService xhOrderService;
    @Autowired
    private XhCouponService xhCouponService;
    @Autowired
    private XhCouponReceiveService xhCouponReceiveService;
    @Autowired
    private XhCouponLogsService xhCouponLogsService;
    @Autowired
    private XhUserService xhUserService;
    /**
     * 写入微信用户
     */
    @CrossOrigin
    @RequestMapping(value = "/setUser",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "xhUser", value="商品对象" ,dataType="{code（微信code）: \"1\"\n" +
                    "nikName(昵称): \"测试\"\n" +
                    "gender（性别）: \"男\"\n" +
                    "sellId（销售编号）: \"1\"\n" +
                    "shopId（商家编号）: \"1\"\n" +
                    "parentId （上级会员id）:\"1\"\n" +
                    "phone（电话号码）:\"123456789\"\n"
                    )
    }
    )
    @ApiOperation(value="写入微信用户", response= XhUserEntity.class)
    public R setUser(@RequestParam Map<String, Object> params)throws Exception{
        XhUserEntity xhUserEntity = new XhUserEntity();
        //调用接口获取openId
        String openId = AppletPayUtil.getOpenIdByCode(params.get("code").toString());
        xhUserEntity.setOpenId(openId);
        xhUserEntity.setPhone(params.get("phone").toString());
        xhUserEntity.setNikName(params.get("nikName").toString());
        xhUserEntity.setGender(params.get("gender").toString());
        String shopId = ObjectUtils.toString(params.get("shopId"), "");
        if(StringUtils.isNotBlank(shopId)){
            xhUserEntity.setShopId(Integer.parseInt(shopId));
        }
        String sellId = ObjectUtils.toString(params.get("sellId"), "");
        if(StringUtils.isNotBlank(sellId)){
            xhUserEntity.setSellId(Integer.parseInt(sellId));
        }
        String parentId = ObjectUtils.toString(params.get("parentId"), "");
        if(StringUtils.isNotBlank(parentId)){
            xhUserEntity.setParentId(Integer.parseInt(parentId));
        }
        try {
            String access_token = WxQrcode.postToken();
            String url = WxQrcode.postMiniqrQr(openId,access_token);
            xhUserEntity.setQrcode(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        xhUserEntity.setUserType(0);
        xhUserEntity.setLevel(0);
        xhUserEntity.setPoint(0);
        xhUserService.save(xhUserEntity);
        String token = JwtUtils.encode(xhUserEntity.getId().toString());
        return R.ok().put("User",xhUserEntity).put("token",token);
    }
    /**
     * 检验用户是否存在
     */
    @CrossOrigin
    @RequestMapping(value = "/checkUser",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value="用户code/openId" ,dataType="{code:123434,openId:1231546}"
            )
    }
    )
    @ApiOperation(value="检验用户", response= XhUserEntity.class)
    public R checkUser(@RequestParam Map<String, Object> params)throws Exception{
        if((StringUtils.isNotBlank((String)params.get("code")))){
            //调用接口获取openId
            String openId = AppletPayUtil.getOpenIdByCode(params.get("code").toString());
            params.put("openId",openId);
        }else{
            if(StringUtils.isBlank((String)params.get("openId"))){
                return R.error().put("msg","参数错误");
            }
        }
        XhUserEntity xhUserEntity = xhUserService.checkUser(params);
        String token="";
        if(xhUserEntity!=null){
            token = JwtUtils.encode(xhUserEntity.getId().toString());
        }
        return R.ok().put("xhUserEntity",xhUserEntity).put("token",token);
    }
    /**
     * 修改用户信息
     */
    @CrossOrigin
    @RequestMapping(value = "/UpdateUser",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "xhUser", value="用户id" ,dataType="{id:1,phone:12345678910}"
            )
    }
    )
    @ApiOperation(value="修改用户信息", response= XhUserEntity.class)
    public R UserInfo(@RequestBody XhUserEntity xhUser, HttpServletRequest request){
        String token = request.getHeader("token");// HttpServletRequest 请求头中取出 token
        String id = JwtUtils.decode(token);
        if(id.equals(String.valueOf(xhUser.getId()))){
            ValidatorUtils.validateEntity(xhUser);
            XhUserEntity updateUser = xhUserService.getById(xhUser.getId());
            updateUser.setPhone(xhUser.getPhone());
            updateUser.setShopId(xhUser.getShopId());
            xhUserService.updateById(updateUser);
            return R.ok().put("xhUser",updateUser);
        }else{
            return R.ok().put("msg","token失效，请重新登录");
        }

    }
    /**
     * 轮播图列表
     */
    @CrossOrigin
    @RequestMapping(value = "/imgList",method = RequestMethod.GET)
    @ApiOperation(value="图片信息", response= XhImageEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value="分页参数" ,dataType="{page:10,limt:1}")
    }
    )
    public R imgList(@RequestParam Map<String, Object> params ) throws Exception{
        PageUtils page = xhImageService.queryPage(params);
        return R.ok().put("page", page);
    }
    /**
     * 获取门店
     */
    @CrossOrigin
    @RequestMapping(value = "/deptList",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value="分页参数" ,dataType="{page（页数）:1,limt（条数）:10,userLat:经度，userLng:纬度}")
    }
    )
    @ApiOperation(value="门店信息", response= SysDeptEntity.class)
    public R depList(@RequestParam Map<String, Object> params) throws Exception{
        String str1 = ObjectUtils.toString(params.get("userLat"), "");
        String str2 = ObjectUtils.toString(params.get("userLng"), "");
        if(StringUtils.isNotBlank(str1)&&StringUtils.isNotBlank(str2)){
            PageUtils page = deptService.apiQueryPage(params);
            return R.ok().put("page", page);
        }
        return R.error().put("msg","坐标参数有误");
    }


    /**
     * 获取具体门店
     */
    @RequestMapping(value = "/deptInfo/{deptId}",method =RequestMethod.GET )
    @ApiOperation(value="具体门店信息", response= SysDictEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value="门店id" ,dataType="deptInfo/1")
    }
    )
    public R deptInfo(@PathVariable("deptId") Long deptId){
        SysDeptEntity dept = deptService.getById(deptId);
        return R.ok().put("dept", dept);
    }



    /**
     * 获取科目
     */
    @CrossOrigin
    @RequestMapping(value = "/dictList",method = RequestMethod.GET)
    @ApiOperation(value="项目信息", response= SysDictEntity.class)
    public List<SysDictEntity> ParentList(){
        List<SysDictEntity> deptList = sysDictService.queryCategoryList(new HashMap<String, Object>());
        return deptList;
    }
    /**
     * 获取商品
     */
    /**
     * 列表
     */
    @CrossOrigin
    @RequestMapping(value = "/goodsList",method = RequestMethod.GET)
    @ApiOperation(value="商品信息", response= SysDictEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value="分页参数" ,dataType="{page（页数）:1,limt（条数）:10,deptName:协禾口腔，dictName:洗牙,}")
    })
    public R goodsList(@RequestParam Map<String, Object> params) throws Exception {
        params.put("status","0");
        PageUtils page = xhGoodsService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 优惠券信息
     */
    @CrossOrigin
    @RequestMapping(value = "/couponList",method =RequestMethod.GET )
    @ApiOperation(value="优惠券信息", response= XhCouponEntity.class)
    public R couponList(@RequestParam Map<String, Object> params)throws Exception{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params.put("date",formatter.format(new Date()));
        PageUtils page = xhCouponService.queryPage(params);
        return R.ok().put("page", page);
    }
    /**
     * 获取优惠券
     *
     */
    @CrossOrigin
    @RequestMapping(value = "/setCoupon",method =RequestMethod.POST )
    @ApiOperation(value="获取优惠券", response= XhCouponEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "xhCouponReceive", value="商品对象" ,dataType="{buyerId（买家id）: \"1\"\n" +
                    "couponId（优惠券id）: \"2132332\"\n" +
                    "couponMoney（优惠券价格）: \"100\"\n" +
                    "fullMoney（满足条件）: \"500\"\n" +
                    "shareId（分享id）: \"1\"\n" +
                    "status（优惠卷状态）: \"0\"}")
    }
    )
    public R getCoupon(@RequestBody XhCouponReceiveEntity xhCouponReceive,HttpServletRequest request)throws Exception{

        XhCouponReceiveEntity xhCouponReceiveEntity = xhCouponReceiveService.checkCoupon(xhCouponReceive);
        String token = request.getHeader("token");// HttpServletRequest 请求头中取出 token
        String id = JwtUtils.decode(token);
        if(id.equals(String.valueOf(xhCouponReceive.getBuyerId()))){
            String msg="";
            if(xhCouponReceiveEntity==null){
                xhCouponReceive.setCreateTime(new Date());
                xhCouponReceiveService.save(xhCouponReceive);
                msg="优惠券领取成功";
            }else{
                msg="优惠券已领取";
            }
            return R.ok().put("msg",msg);
        }else{
            return R.ok().put("msg","token失效，请重新登录");
        }


    }

    /**
     * 个人优惠券信息
     */
    @CrossOrigin
    @RequestMapping(value = "/couponInfo",method =RequestMethod.GET )
    @ApiOperation(value="优惠券信息", response= XhCouponEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value="参数类型 1已使用 0未使用 -1已过期" ,dataType="{type(类型):1,page（页数）:0,limt（条数）:10,id（编号）:1}")
    }
    )
    public R couponInfo(@RequestParam Map<String, Object> params,HttpServletRequest request)throws Exception{
        String token = request.getHeader("token");// HttpServletRequest 请求头中取出 token
        String id = JwtUtils.decode(token);
        if(id.equals(params.get("id").toString())){
            String type = (String)params.get("type");
            params.put("status",type);
            PageUtils page = xhCouponReceiveService.queryPage(params);
            return R.ok().put("page", page);
        }else {
            return R.ok().put("msg","token失效，请重新登录");
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/m/order",method = RequestMethod.POST)
    @ApiOperation(value = "支付订单", notes = "支付订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "XhOrderEntity", value="订单对象" ,dataType="{userId（用户id）: \"1\"\n" +
                    "orderId（订单号）: \"1\"\n" +
                    "pid（支付类型）: \"1\"\n" +
                    "totalPrices（总价）: \"100\"\n" +
                    "goodsId（商品编号）: \"500\"\n" +
                    "payPrice（支付总价）: \"1\"\n" +
                    "deptId（店铺id）: \"1\"\n" +
                    "couponId(优惠券id):\"1\"\n"+
                    "couponPrice(优惠价格): \"100\"\n" +
                    "goodsCount(商品数量): \"2\"\n"
            )
    })
    @PostMapping
    public R order(@RequestBody XhOrderEntity xhOrderEntity,HttpServletRequest request) throws Exception {
        /**
         微信小程序支付
         */
        int userId = xhOrderEntity.getUserId();
        String token = request.getHeader("token");// HttpServletRequest 请求头中取出 token
        String id = JwtUtils.decode(token);
        if(id.equals(String.valueOf(userId))){
            xhOrderEntity.setStatus(0);
            xhOrderEntity.setDeleteMark(0);
            XhUserEntity updateUser = xhUserService.getById(userId);
            BigDecimal money = xhOrderEntity.getPayPrice();
            //获取优惠卷数据
            if(xhOrderEntity.getCouponId()!=0){
                XhCouponReceiveEntity xhCouponReceive = new XhCouponReceiveEntity();
                xhCouponReceive.setCouponId(xhOrderEntity.getCouponId());
                xhCouponReceive.setBuyerId(xhOrderEntity.getUserId().longValue());
                XhCouponReceiveEntity updateCoupon =xhCouponReceiveService.checkCoupon(xhCouponReceive);
                updateCoupon.setStatus(1);
                xhCouponReceiveService.updateById(updateCoupon);
                XhCouponEntity xhCouponEntity = xhCouponService.getById(xhOrderEntity.getCouponId());
                if(xhOrderEntity.getTotalPrices().compareTo(xhCouponEntity.getFullMoney())==1){
                    money = xhOrderEntity.getTotalPrices().subtract(xhCouponEntity.getMoney());
                }
            }
            String openId = updateUser.getOpenId();
        /*
            生成订单....，这里只创建了订单号
         */
            String outTradeNo="";
            if(StringUtils.isBlank(xhOrderEntity.getOrderId())){
                //订单号  uuid
                outTradeNo= IdGen.uuid();
            }else{
                outTradeNo = xhOrderEntity.getOrderId();
            }
            Object packageParams = xhOrderService.unifiedOrder(outTradeNo,money,openId,xhOrderEntity);
            return R.ok().put("packageParams",packageParams).put("outTradeNo",outTradeNo);
        }else{
            return R.ok().put("msg","token失效，请重新登录");
        }

    }
    @CrossOrigin
    @RequestMapping(value = "/m/userOrder",method = RequestMethod.GET)
    @ApiOperation(value="用户订单信息", response= XhOrderEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value="分页参数" ,dataType="{page（页数）:1,limt（条数）:10,userId(用户编号):1,orderId(订单编号):ascsagasdf12321312}")
    })
    public R userOrder(@RequestParam Map<String, Object> params){
        PageUtils page = xhOrderService.queryPage(params);
        return R.ok().put("page", page);
    }
    @CrossOrigin
    @RequestMapping(value = "/m/updateOrder",method = RequestMethod.GET)
    @ApiOperation(value="更新订单信息", response= XhOrderEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value="分页参数" ,dataType="{page（页数）:1,limt（条数）:10,outTradeNo(订单编号):1,type(更新类型（0：更新支付、1：删除订单）):0/1},userId(用户id):1")
    })
    public R updateOrder(@RequestParam Map<String, Object> params,HttpServletRequest request){
        String token = request.getHeader("token");// HttpServletRequest 请求头中取出 token
        String id = JwtUtils.decode(token);
        if(id.equals(params.get("userId").toString())){
            String outTradeNo = (String)params.get("outTradeNo");
            String msg = "";
            if(StringUtils.isBlank(outTradeNo)){
                msg="参数错误！！！";
            }else{
                XhOrderEntity xhOrderEntity = xhOrderService.findByOrderNo(outTradeNo);
                if(xhOrderEntity==null){
                    msg = "订单号未找到";
                    logger.info("订单号未找到",params.get("outTradeNo"));
                }else{
                    if(params.get("type").equals("0")){
                        msg = "未完成支付";
                        xhOrderEntity.setStatus(2);
                        xhOrderService.updateById(xhOrderEntity);
                    }else{
                        //使用过优惠券退优惠券
                        if(xhOrderEntity.getCouponId()!=0){
                            XhCouponReceiveEntity xhCouponReceive = new XhCouponReceiveEntity();
                            xhCouponReceive.setCouponId(xhOrderEntity.getCouponId());
                            xhCouponReceive.setBuyerId(xhOrderEntity.getUserId().longValue());
                            XhCouponReceiveEntity updateCoupon =xhCouponReceiveService.checkCoupon(xhCouponReceive);
                            updateCoupon.setStatus(0);
                            xhCouponReceiveService.updateById(updateCoupon);
                        }
                        msg="订单取消成功";
                        xhOrderEntity.setDeleteMark(1);
                        xhOrderService.updateById(xhOrderEntity);

                    }

                }
            }
            return R.ok().put("msg",msg);
        }else{
            return R.ok().put("msg","token失效，请重新登录");
        }
    }


    public static void main(String[] args) {
//        String outTradeNo= IdGen.uuid();
//        System.out.println(outTradeNo);
        String id = "1";
        int i = 1;
        if(id.equals(String.valueOf(i))){
            System.out.printf("成功");
        }else{
            System.out.printf("失败");
        }


    }

}

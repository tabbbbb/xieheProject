package io.renren.modules.api.controller;

import io.renren.common.sdk.HttpKit;
import io.renren.common.sdk.PaymentKit;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.*;
import io.renren.modules.sys.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/m/pay/")
public class PayApi extends AbstractController {
    @Autowired
    private XhOrderService xhOrderService;
    @Autowired
    private XhCouponReceiveService xhCouponReceiveService;
    @Autowired
    private XhUserService xhUserService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private XhCouponLogsService xhCouponLogsService;
    /**
     * 成功的标识
     */
    private final static String SUCCESS="SUCCESS";

    /**
     * 返回状态码的变量名
     *
     */
    private final static String RETURN_CODE="return_code";
    /**
     * 功能描述: <小程序回调>
     * @return:
     * @auther: majker
     * @date: 2019/3/10
     **/
    @RequestMapping("/wxProPayNotify/anon")
    public void wxProPayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("进入微信小程序支付回调");
        String xmlMsg = HttpKit.readData(request);
        logger.info("微信小程序通知信息"+xmlMsg);
        Map<String, String> resultMap = PaymentKit.xmlToMap(xmlMsg);
        logger.info(resultMap.toString());
        if(resultMap.get(RETURN_CODE).equals(SUCCESS)){
            String orderNo = resultMap.get("out_trade_no");
            logger.info("微信小程序支付成功,订单号{}",orderNo);
            /**
             *  通过订单号 修改数据库中的记录，此处省略n行代码
             */
            XhOrderEntity xhOrderEntity = xhOrderService.findByOrderNo(orderNo);
            if(xhOrderEntity==null){
                logger.info("订单号未找到",orderNo);
            }else{
                //分销积分分配
                logger.info("开始积分分配");
                BigDecimal price = xhOrderEntity.getPayPrice();
                //获取对应的上下级用户ID分配积分
                XhUserEntity xhUserEntity = xhUserService.getById(xhOrderEntity.getUserId());
                if(xhUserEntity.getShopId()!=null){
                    SysDeptEntity sysDeptEntity = sysDeptService.getById(xhUserEntity.getShopId());
                    sysDeptEntity.setShopPoint(sysDeptEntity.getShopPoint()+1);
                    sysDeptService.updateById(sysDeptEntity);
                }
                if(xhUserEntity.getSellId()!=null){
                    XhUserEntity sellUser = xhUserService.getById(xhUserEntity.getSellId());
                    sellUser.setPoint(sellUser.getPoint()+1);
                    xhUserService.updateById(sellUser);
                }
                if(xhUserEntity.getParentId()!=null){
                    XhUserEntity parentUser = xhUserService.getById(xhUserEntity.getParentId());
                    parentUser.setPoint(parentUser.getPoint()+1);
                    xhUserService.updateById(parentUser);
                }
//                logger.info("开始更新优惠卷数据");
//                XhCouponReceiveEntity xhCouponReceive = new XhCouponReceiveEntity();
//                if(xhOrderEntity.getCouponId()!=0){
//                    xhCouponReceive.setCouponId(xhOrderEntity.getCouponId());
//                    xhCouponReceive.setBuyerId(xhOrderEntity.getUserId().longValue());
//                    XhCouponReceiveEntity updateCoupon =xhCouponReceiveService.checkCoupon(xhCouponReceive);
//                    updateCoupon.setStatus(1);
//                    xhCouponReceiveService.updateById(updateCoupon);
////                    logger.info("开始新增优惠卷log数据");
////                    XhCouponLogsEntity xhCouponLogsEntity = new XhCouponLogsEntity();
////                    xhCouponLogsEntity.setBuyerId(xhOrderEntity.getUserId().longValue());
////                    xhCouponLogsEntity.setShareId(updateCoupon.getShareId());
////                    xhCouponLogsEntity.setCouponReceiveId(updateCoupon.getId());
////                    xhCouponLogsEntity.setOrderNumber(orderNo);
////                    xhCouponLogsEntity.setOrderOriginalAmount(xhOrderEntity.getTotalPrices());
////                    xhCouponLogsEntity.setOrderFinalAmount(xhOrderEntity.getPayPrice());
////                    xhCouponLogsEntity.setCouponAmount(xhCouponReceive.getCouponMoney());
////                    xhCouponLogsEntity.setCreateTime(new Date());
////                    xhCouponLogsEntity.setStatus(1);
////                    xhCouponLogsService.save(xhCouponLogsEntity);
//                }

                logger.info("更新订单数据");
                xhOrderEntity.setStatus(1);
                xhOrderService.updateById(xhOrderEntity);
            }
        }
        String result =  "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

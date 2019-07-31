package io.renren.modules.api.controller;

import io.renren.common.sdk.HttpKit;
import io.renren.common.sdk.PaymentKit;
import io.renren.modules.sys.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("m/pay/")
public class PayApi extends AbstractController {
    /**
     * 成功的标识
     */
    private final static String SUCCESS="SUCCESS";

    /**
     * 返回状态码的变量名
     *
     */
    private final static String RETURN_CODE="RETURN_CODE";
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
        if(resultMap.get(RETURN_CODE).equals(SUCCESS)){
            String orderNo = resultMap.get("out_trade_no");
            logger.info("微信小程序支付成功,订单号{}",orderNo);
            /**
             *  通过订单号 修改数据库中的记录，此处省略n行代码
             */
        }
        String result =  "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

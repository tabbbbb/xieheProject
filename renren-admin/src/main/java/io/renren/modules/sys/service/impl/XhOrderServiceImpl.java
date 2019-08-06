package io.renren.modules.sys.service.impl;

import io.renren.common.config.WxConfig;
import io.renren.common.sdk.PaymentApi;
import io.renren.common.sdk.PaymentKit;
import io.renren.common.sdk.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.XhOrderDao;
import io.renren.modules.sys.entity.XhOrderEntity;
import io.renren.modules.sys.service.XhOrderService;


@Service("xhOrderService")
public class XhOrderServiceImpl extends ServiceImpl<XhOrderDao, XhOrderEntity> implements XhOrderService {
    @Autowired
    private XhOrderService xhOrderService;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<XhOrderEntity> page = this.page(
                new Query<XhOrderEntity>().getPage(params),
                new QueryWrapper<XhOrderEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    public Object unifiedOrder(String outTradeNo, BigDecimal money, String openid,XhOrderEntity xhOrderEntity) throws Exception {
        Map<String, String> reqParams = new HashMap<>();
        //微信分配的小程序ID
        reqParams.put("appid", WxConfig.APPID);
        //微信支付分配的商户号
        reqParams.put("mch_id", WxConfig.MCH_ID);
        //随机字符串
        reqParams.put("nonce_str", System.currentTimeMillis() / 1000 + "");
        //签名类型
        reqParams.put("sign_type", "MD5");
        //充值订单 商品描述
        reqParams.put("body",  "-充值订单-微信小程序");

        //商户订单号
        reqParams.put("out_trade_no", outTradeNo);
        //订单总金额，单位为分
        reqParams.put("total_fee", money.multiply(BigDecimal.valueOf(100)).intValue() + "");
        //终端IP
        reqParams.put("spbill_create_ip", "127.0.0.1");
        //通知地址
        reqParams.put("notify_url", WxConfig.NOTIFY_URL);
        //交易类型
        reqParams.put("trade_type", "JSAPI");
        //用户标识
        reqParams.put("openid", openid);
        //签名
        String sign = WXPayUtil.generateSignature(reqParams, WxConfig.KEY);
        reqParams.put("sign", sign);
        /*
            调用支付定义下单API,返回预付单信息 prepay_id
         */
        String xmlResult = PaymentApi.pushOrder(reqParams);
        logger.info(xmlResult);
        Map<String, String> result = PaymentKit.xmlToMap(xmlResult);
        //预付单信息
        String prepay_id = result.get("prepay_id");
        xhOrderEntity.setOrderTime(new Date());
        xhOrderService.save(xhOrderEntity);
        /*
            小程序调起支付数据签名
         */
        Map<String, String> packageParams = new HashMap<String, String>();
        packageParams.put("appId", WxConfig.APPID);
        packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
        packageParams.put("nonceStr", System.currentTimeMillis() + "");
        packageParams.put("package", "prepay_id=" + prepay_id);
        packageParams.put("signType", "MD5");
        String packageSign = WXPayUtil.generateSignature(packageParams, WxConfig.KEY);
        packageParams.put("paySign", packageSign);
        return packageParams;

    }


}

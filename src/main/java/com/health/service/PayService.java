package com.health.service;

import com.alipay.api.AlipayApiException;
import com.health.entity.AlipayBean;

public interface PayService {

    String aliPay(AlipayBean alipayBean) throws AlipayApiException;

}

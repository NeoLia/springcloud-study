package org.mjh.springcloud.service;

import org.mjh.springcloud.BaseResult;
import org.mjh.springcloud.fallback.PaymentV1ServiceFallbackFactory;
import org.mjh.springcloud.po.PaymentRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Neo Lia Marx
 * @date: 2022/3/11 20:31
 */
@Service
@FeignClient(value = "PROVIDER-PAYMENT-SERVICE", fallbackFactory = PaymentV1ServiceFallbackFactory.class)
public interface PaymentService {
    @GetMapping(value = "/v1/payment-records/{orderNo}")
    BaseResult<PaymentRecord> getPayment(@PathVariable("orderNo") String orderNo);
}

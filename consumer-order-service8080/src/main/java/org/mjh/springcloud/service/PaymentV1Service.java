package org.mjh.springcloud.service;

import org.mjh.springcloud.BaseResult;
import org.mjh.springcloud.po.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Neo Lia Marx
 * @date: 2022/3/11 20:31
 */
@Service
@FeignClient(value = "PROVIDER-PAYMENT-SERVICE")
public interface PaymentV1Service {
    @GetMapping(value = "/v1/payments/{orderNo}")
    BaseResult<Payment> getPayment(@PathVariable("orderNo") String orderNo);

    @GetMapping(value = "/v1/payments/timeout")
    BaseResult<Payment> getPaymentTimeout();
}

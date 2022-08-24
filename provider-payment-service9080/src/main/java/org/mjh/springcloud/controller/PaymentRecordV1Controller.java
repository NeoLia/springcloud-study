package org.mjh.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.mjh.springcloud.BaseResult;
import org.mjh.springcloud.po.PaymentRecord;
import org.mjh.springcloud.service.PaymentRecordV1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/24 21:51
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class PaymentRecordV1Controller {
    private final PaymentRecordV1Service paymentRecordV1Service;

    @Value("${server.port}")
    private String severPort;

    @Autowired
    public PaymentRecordV1Controller(PaymentRecordV1Service paymentRecordV1Service) {
        this.paymentRecordV1Service = paymentRecordV1Service;
    }

    @GetMapping(value = "/payment-records/{orderNo}")
    @HystrixCommand(fallbackMethod = "getPaymentRecordFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public BaseResult<PaymentRecord> getPaymentRecord(@PathVariable("orderNo") String orderNo) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        PaymentRecord paymentRecord = paymentRecordV1Service.getPaymentRecord(orderNo);
        if (paymentRecord == null) {
            throw new RuntimeException("Cannot find payment record by orderNo " + orderNo);
        }
        return new BaseResult<>("0", "ok: " + severPort, true, paymentRecord);
    }

    public BaseResult<PaymentRecord> getPaymentRecordFallback(String orderNo) {
        log.warn("Fallback: Cannot find the payment record by orderNo {}", orderNo);
        return BaseResult.fail("Payment Service-Provider: [无法获取支付记录]");
    }
}

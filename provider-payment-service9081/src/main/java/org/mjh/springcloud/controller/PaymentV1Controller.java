package org.mjh.springcloud.controller;

import org.mjh.springcloud.BaseResult;
import org.mjh.springcloud.po.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.mjh.springcloud.service.PaymentService;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/24 21:51
 */
@RestController
@RequestMapping("/v1")
public class PaymentV1Controller {
    private final PaymentService paymentService;

    @Value("${server.port}")
    private String severPort;

    @Autowired
    public PaymentV1Controller(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/payments/{orderNo}")
    public BaseResult<Payment> getPayment(@PathVariable("orderNo") String orderNo) {
        Payment payment = paymentService.getPayment(orderNo);
        return new BaseResult<>("0", "ok: " + severPort, true, payment);
    }

    @GetMapping(value = "/payments/timeout")
    public BaseResult<Payment> getPaymentTimeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BaseResult<>("0", severPort, true);
    }
}

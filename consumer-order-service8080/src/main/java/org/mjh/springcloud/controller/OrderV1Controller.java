package org.mjh.springcloud.controller;

import org.mjh.springcloud.BaseResult;
import org.mjh.springcloud.po.Order;
import org.mjh.springcloud.po.Payment;
import org.mjh.springcloud.service.OrderV1Service;
import org.mjh.springcloud.service.PaymentV1Service;
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
public class OrderV1Controller {
    private final PaymentV1Service paymentV1Service;
    private final OrderV1Service orderV1Service;

    public OrderV1Controller(PaymentV1Service paymentV1Service, OrderV1Service orderV1Service) {
        this.paymentV1Service = paymentV1Service;
        this.orderV1Service = orderV1Service;
    }

    @GetMapping(value = "/orders/{orderNo}")
    public Order getOrder(@PathVariable("orderNo") String orderNo) {
        return orderV1Service.getOrderByOrderNo(orderNo);
    }

    @GetMapping(value = "/orders/{orderNo}/payment")
    public BaseResult<Payment> getOrderWithPayment(@PathVariable("orderNo") String orderNo) {
        BaseResult<Payment> paymentBaseResult = paymentV1Service.getPayment(orderNo);
        return paymentBaseResult;
    }

    @GetMapping(value = "/orders/payments/timeout")
    public BaseResult<Payment> getPaymentTimeout() {
        BaseResult<Payment> paymentBaseResult = paymentV1Service.getPaymentTimeout();
        return paymentBaseResult;
    }
}

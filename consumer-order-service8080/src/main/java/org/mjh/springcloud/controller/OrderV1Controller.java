package org.mjh.springcloud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.mjh.springcloud.BaseResult;
import org.mjh.springcloud.po.Order;
import org.mjh.springcloud.po.PaymentRecord;
import org.mjh.springcloud.service.OrderV1Service;
import org.mjh.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/24 21:51
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class OrderV1Controller {
    private final PaymentService paymentService;
    private final OrderV1Service orderV1Service;
    private final ObjectMapper objectMapper;

    public OrderV1Controller(PaymentService paymentService, OrderV1Service orderV1Service, ObjectMapper objectMapper) {
        this.paymentService = paymentService;
        this.orderV1Service = orderV1Service;
        this.objectMapper = objectMapper;
    }

    /**
     * Get order by orderNo
     * @param orderNo
     * @return BaseResult<Order>
     */
    @GetMapping(value = "/orders/{orderNo}")
    @HystrixCommand(fallbackMethod = "getOrderFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public BaseResult<Order> getOrder(@PathVariable("orderNo") String orderNo) {
        Order order = orderV1Service.getOrderByOrderNo(orderNo);
        if (order == null) {
            throw new RuntimeException("Cannot find order by orderNo " + orderNo);
        }
        return BaseResult.ok(order);
    }

    /**
     * Get Order and order payment record by orderNo
     * @param orderNo
     * @return BaseResult<Map<String, Object>>
     * @throws JsonProcessingException - When failed to parse object to json string.
     */
    @GetMapping(value = "/orders/{orderNo}/payment")
    @HystrixCommand(fallbackMethod = "getOrderWithPaymentFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public BaseResult<Map<String, Object>> getOrderWithPayment(@PathVariable("orderNo") String orderNo)
            throws JsonProcessingException {
        try {
            BaseResult<PaymentRecord> paymentRecordResult = paymentService.getPayment(orderNo);
            if (!paymentRecordResult.isSuccess()) {
                log.warn("{}", objectMapper.writeValueAsString(paymentRecordResult));
            }
            Order order = orderV1Service.getOrderByOrderNo(orderNo);
            if (!paymentRecordResult.isSuccess() && order == null) {
                throw new RuntimeException("Cannot find order by orderNo " + orderNo);
            }

            Map<String, Object> dataMap = new HashMap<>(2);
            dataMap.put("paymentRecord", paymentRecordResult.getData());
            dataMap.put("order", order);
            return BaseResult.ok(dataMap);
        } catch (Exception e) {
            log.error("Throwing exception in getting order with payment record.", e);
            throw e;
        }
    }

    /**
     * Fallback: GET /v1/orders/{orderNo}
     * @param orderNo
     * @return
     */
    public BaseResult<Order> getOrderFallback(String orderNo) {
        log.warn("Fallback: Cannot find order by orderNo {}", orderNo);
        return BaseResult.fail("Order Service-Provider: [无法查询订单: " + orderNo + "]");
    }

    /**
     * Fallback: GET /v1/orders/{orderNo}/payment
     * @param orderNo
     * @return
     */
    public BaseResult<Map<String, Object>> getOrderWithPaymentFallback(String orderNo) {
        log.warn("Fallback: Cannot find order and payment record by orderNo {}", orderNo);
        return BaseResult.fail("Order Service-Provider [无法查询订单和支付记录: " + orderNo + "]");
    }
}

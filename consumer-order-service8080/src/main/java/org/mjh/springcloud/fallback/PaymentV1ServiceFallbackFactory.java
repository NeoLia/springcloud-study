package org.mjh.springcloud.fallback;

import lombok.extern.slf4j.Slf4j;
import org.mjh.springcloud.BaseResult;
import org.mjh.springcloud.po.PaymentRecord;
import org.mjh.springcloud.service.PaymentService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Neo Lia Marx
 * @date: 2022/8/20 18:53
 */
@Component
@Slf4j
public class PaymentV1ServiceFallbackFactory implements FallbackFactory<PaymentService> {
    @Override
    public PaymentService create(Throwable cause) {
        return new PaymentService() {
            @Override
            public BaseResult<PaymentRecord> getPayment(String orderNo) {
                log.warn("Fallback: Api call - PROVIDER-PAYMENT-SERVICE/payment-records/{orderNo}", cause);
                return BaseResult.fail("Order Service-Consumer: [无法调用服务: PROVIDER-PAYMENT-SERVICE/payment-records/{orderNo}]");
            }
        };
    }
}

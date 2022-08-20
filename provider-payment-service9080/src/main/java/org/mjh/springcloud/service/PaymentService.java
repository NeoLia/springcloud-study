package org.mjh.springcloud.service;

import org.mjh.springcloud.po.Payment;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/25 21:47
 */
public interface PaymentService {
    Payment getPayment(String orderNo);
}

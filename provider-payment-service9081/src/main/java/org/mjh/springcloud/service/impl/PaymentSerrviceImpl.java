package org.mjh.springcloud.service.impl;

import org.mjh.springcloud.po.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mjh.springcloud.dao.PaymentDao;
import org.mjh.springcloud.service.PaymentService;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/25 21:47
 */
@Service
public class PaymentSerrviceImpl implements PaymentService {
    private final PaymentDao paymentDao;

    @Autowired
    public PaymentSerrviceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public Payment getPayment(String orderNo) {
        return paymentDao.selectByOrderNo(orderNo);
    }
}

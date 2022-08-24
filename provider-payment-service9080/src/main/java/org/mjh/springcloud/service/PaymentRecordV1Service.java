package org.mjh.springcloud.service;

import org.mjh.springcloud.dao.PaymentRecordDao;
import org.mjh.springcloud.po.PaymentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/25 21:47
 */
@Service
public class PaymentRecordV1Service {
    private final PaymentRecordDao paymentDao;

    @Autowired
    public PaymentRecordV1Service(PaymentRecordDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public PaymentRecord getPaymentRecord(String orderNo) {
        return paymentDao.selectByOrderNo(orderNo);
    }
}

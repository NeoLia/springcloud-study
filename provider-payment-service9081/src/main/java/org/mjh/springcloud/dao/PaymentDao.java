package org.mjh.springcloud.dao;

import org.apache.ibatis.annotations.Param;
import org.mjh.springcloud.po.Payment;
import org.springframework.stereotype.Repository;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/25 14:29
 */
@Repository
public interface PaymentDao {
    Payment selectByOrderNo(@Param("orderNo") String orderNo);
}

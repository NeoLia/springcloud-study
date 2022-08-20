package org.mjh.springcloud.dao;

import org.apache.ibatis.annotations.Param;
import org.mjh.springcloud.po.Order;
import org.springframework.stereotype.Repository;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/25 15:01
 */
@Repository
public interface OrderDao {
    Order selectByOrderNo(@Param("orderNo") String orderNo);
}

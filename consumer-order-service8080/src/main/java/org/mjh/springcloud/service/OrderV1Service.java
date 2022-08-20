package org.mjh.springcloud.service;

import org.mjh.springcloud.dao.OrderDao;
import org.mjh.springcloud.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Neo Lia Marx
 * @date: 2022/8/20 14:41
 */
@Service
public class OrderV1Service {
    private final OrderDao orderDao;

    @Autowired
    public OrderV1Service(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order getOrderByOrderNo(String orderNo) {
        return orderDao.selectByOrderNo(orderNo);
    }
}

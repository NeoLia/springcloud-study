package org.mjh.springcloud.po;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/24 22:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PaymentRecord {
    private int id;
    private String paymentNo;
    private String orderNo;
    private BigDecimal summary;
}

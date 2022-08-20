package org.mjh.springcloud.po;

import lombok.*;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/24 22:08
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Order {
    private int id;
    private String orderNo;
    private String customerName;
}

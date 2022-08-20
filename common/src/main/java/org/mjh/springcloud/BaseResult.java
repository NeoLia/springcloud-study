package org.mjh.springcloud;

import lombok.*;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/25 22:00
 */
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class BaseResult<T> {
    @NonNull
    private String code;
    @NonNull
    private String msg;
    @NonNull
    private boolean success;
    private T data;
}

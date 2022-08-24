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

    public static <T> BaseResult<T> ok(T data) {
        return new BaseResult<T>("0", "ok", true, data);
    }

    public static <T> BaseResult<T> ok() {
        return new BaseResult<T>("0", "ok", true);
    }

    public static <T> BaseResult<T> fail(String code, String msg) {
        return new BaseResult<>(code, msg, false);
    }

    public static <T> BaseResult<T> fail(String msg) {
        return new BaseResult<>("-1", msg, false);
    }
}

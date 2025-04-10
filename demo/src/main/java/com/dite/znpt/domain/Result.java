package com.dite.znpt.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @description: 返回结果
 */
@Data
public class Result<T> implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Result.class);
    private static final String SUCCESS = "success";
    private static final Integer SUCCESS_CODE = 200;

    private int status;

    private T data;

    private String msg;

    private Integer code;

    public Result(HttpStatus status, T data, Integer code, String msg) {
        this.status = status.value();
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return this.getCode().equals(SUCCESS_CODE);
    }

    public static <T> Result<T> okM(String msg) {
        return new Result<>(HttpStatus.OK, null, SUCCESS_CODE, msg);
    }

    public static <T> Result<T> ok() {
        return new Result<>(HttpStatus.OK, null, SUCCESS_CODE, StringUtils.EMPTY);
    }

    public static <T> Result<T> okM(T t, String msg) {
        return new Result<>(HttpStatus.OK, t, SUCCESS_CODE, msg);
    }

    public static <T> Result<T> ok(T t) {
        return new Result<>(HttpStatus.OK, t, SUCCESS_CODE, StringUtils.EMPTY);
    }

    public static Result<Boolean> okTrue() {
        return new Result<Boolean>(HttpStatus.OK, Boolean.TRUE, SUCCESS_CODE, StringUtils.EMPTY);
    }

    public static Result<Boolean> okFalse() {
        return new Result<Boolean>(HttpStatus.OK, Boolean.FALSE, SUCCESS_CODE, StringUtils.EMPTY);
    }

    public static <T> Result<T> warn(String code, String msg) {
        return new Result<T>(HttpStatus.INTERNAL_SERVER_ERROR, null, Integer.parseInt(code), msg);
    }

}

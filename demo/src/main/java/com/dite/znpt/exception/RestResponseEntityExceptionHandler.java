package com.dite.znpt.exception;

import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 全局异常处理
 */
@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final Result<?> handler(Exception e) {
        logger.error("出现系统异常：{},异常类型：{},详细堆栈：", e.getMessage(), e.getClass(), e);
        return Result.error(Constants.SYSTEM_EXCEPTION, Constants.SERVICE_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(ServiceException.class)
    public final Result<?> handler(ServiceException e) {
        logger.error("出现服务异常：{},异常类型：{},详细堆栈：", e.getMessage(), e.getClass(), e);
        return Result.error(Constants.SERVICE_EXCEPTION, e.getMessage());
    }

    /**
     * @author wujinsong
     * @date 2021/10/9 1:28 下午
     * @description: 数据效验的异常处理
     * @Param [e]
     * @Return R
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> handleValidException(MethodArgumentNotValidException e) {
        logger.error("数据效验出现了异常：{},异常类型：{},详细堆栈：", e.getMessage(), e.getClass(), e);
        StringBuilder sb = new StringBuilder();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getFieldErrors().forEach((fieldError) -> {
            sb.append(fieldError.getField()).append(fieldError.getDefaultMessage()).append(";");
        });

        return Result.error(Constants.PARAMETER_EXCEPTION, sb.toString());
    }
}

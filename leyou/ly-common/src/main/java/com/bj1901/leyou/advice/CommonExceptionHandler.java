package com.bj1901.leyou.advice;

import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 15:45
 */
@ControllerAdvice   // contoller层异常通知的注解
public class CommonExceptionHandler {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> HandlerException(LyException e) {
        // 分组异常结果对象
        ExceptionResult exceptionResult = new ExceptionResult(e.getExceptionEnum());
        return ResponseEntity.status(exceptionResult.getStatus())
                .body(exceptionResult);
    }

}

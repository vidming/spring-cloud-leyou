package com.bj1901.leyou.vo;

import com.bj1901.leyou.enums.ExceptionEnum;
import lombok.Data;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 16:06
 */
@Data
public class ExceptionResult {

    private Integer status;
    private String message;
    private Long timeStamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timeStamp = System.currentTimeMillis();
    }

}

package com.bj1901.leyou.custom_exception;

import com.bj1901.leyou.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 15:48
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LyException extends RuntimeException {

    private ExceptionEnum exceptionEnum;
}

package com.bj1901.leyou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页的javaBean
 *
 * @Author: adming
 * @Date: 2019/5/7 0007 11:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private Long total;  // 总记录数
    private Long totalPage;   // 总页数
    private Long currentPage; // 当前页码
    private Long pageSize;    // 当前页要显示的记录数
    private List<T> items;    // 当前页显示的记录数据
}

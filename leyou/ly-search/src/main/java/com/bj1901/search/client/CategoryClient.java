package com.bj1901.search.client;

import com.bj1901.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 14:45
 */
@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}

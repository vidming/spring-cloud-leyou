package com.bj1901.search.client;

import com.bj1901.item.api.SpecApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 18:55
 */
@FeignClient(value = "item-service")
public interface SpecClient extends SpecApi {
}

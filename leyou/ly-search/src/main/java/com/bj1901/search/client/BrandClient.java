package com.bj1901.search.client;

import com.bj1901.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 17:37
 */
@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi{
}

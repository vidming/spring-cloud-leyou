package com.bj1901.item.service.impl;

import com.bj1901.item.domain.Stock;
import com.bj1901.item.mapper.StockMapper;
import com.bj1901.item.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存表，代表库存，秒杀库存等信息 服务实现类
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

}

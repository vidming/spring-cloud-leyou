package com.bj1901.item;

import com.bj1901.item.mapper.ICategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: adming
 * @Date: 2019/7/1 0001 12:50
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class CategoryMapperTest {

    @Autowired
    private ICategoryMapper categoryMapper;

    @Test
    public void testQueryCidsByBid() {
        Long bid = 7817L;
        List<Long> cids = categoryMapper.queryCidsByBid(bid);
        System.out.println(cids);
    }


}

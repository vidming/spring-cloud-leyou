package com.bj1901.item;

import com.bj1901.item.service.ISpuService;
import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.vo.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: adming
 * @Date: 2019/7/3 0003 14:47
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class SpuServiceImplTest {

    @Autowired
    private ISpuService spuService;

    @Test
    public void findSpuByPage() {

        PageResult<SpuVo> spuByPage = spuService.findSpuByPage(null, 1L, 5L, null);
        spuByPage.getItems().stream().forEach(spuVo -> System.out.println(spuVo));

    }
}
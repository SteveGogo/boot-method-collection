package com.example.caffinedemo;

import com.example.caffinedemo.dto.AddressDTO;
import com.example.caffinedemo.service.AddressService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class CaffineDemoApplicationTests {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCache() {
        // 插入缓存 和数据库
        AddressDTO newInsert = addressService.create("南山大道");
        // 要走缓存
        AddressDTO address = addressService.getAddress(newInsert.getCustomerId());
        long customerId = 2;

        // 第一次未命中缓存，打印 customerId：{} 没有走缓存，开始从数据库查询
        AddressDTO address2 = addressService.getAddress(customerId);
        // 命中缓存
        AddressDTO cacheAddress2 = addressService.getAddress(customerId);

        // 更新数据库和缓存
        addressService.update(customerId, "地址 2 被修改");

        // 更新后查询，依然命中缓存
        AddressDTO hitCache2 = addressService.getAddress(customerId);
        Assert.assertEquals(hitCache2.getAddress(), "地址 2 被修改");

        // 删除缓存
        addressService.delete(customerId);

        // 未命中缓存, 从数据库读取
        AddressDTO hit = addressService.getAddress(customerId);
        System.out.println(hit.getCustomerId());
    }


}

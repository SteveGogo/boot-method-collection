package com.mylearn.designpattern;

import com.mylearn.designpattern.ChainOfResponsibility.ChainPatternDemo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternApplicationTests {

    @Autowired
    ChainPatternDemo chainPatternDemo;

    /**
     * 责任链测试
     */
    @Test
    public void testChainPatternDemo() {
        chainPatternDemo.exec(null, null);
    }

}

package com.example.guavademo;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class JoinerAndSplitTest {

    //按"," 拼接字符串
    public static final Joiner JOINER = Joiner.on(",").skipNulls();

    //按"," 分隔, 去除头尾空格, 忽略 null 和 空串
    public static final Splitter SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();


    public static void main(String[] args) {

        String join = JOINER.join(Lists.newArrayList("1", "   ", "2", "", null,"3"));
        System.out.println(join);

        System.out.println(SPLITTER.split("a,   ,b,,c"));


    }
}

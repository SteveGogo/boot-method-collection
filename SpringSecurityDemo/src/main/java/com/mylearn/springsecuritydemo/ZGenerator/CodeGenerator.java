package com.mylearn.springsecuritydemo.ZGenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://175.178.46.201:3306/security?useUnicode=true&characterEncoding=utf-8" +
                        "&useSSL=false&serverTimezone=GMT%2B8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("steve") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride()// 覆盖已生成文件
                            .outputDir("/Users/steve_gao/GitHubProject/boot-method-collection/SpringSecurityDemo/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.mylearn.springsecuritydemo") // 设置父包名
                            //.moduleName() // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/steve_gao/GitHubProject/boot-method-collection/SpringSecurityDemo/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            .addInclude("sys_permission") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_")
                            .entityBuilder()
                            .enableLombok()
                            .build()

                    ; // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}

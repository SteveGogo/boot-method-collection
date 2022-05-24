package com.mylearn.httpclientdemo.controller;

import com.mylearn.httpclientdemo.domain.Person;
import org.springframework.web.bind.annotation.*;

@RestController
public class Hello {
    @RequestMapping("/hello")
    public String hello() {
        return "hello world from aaaa";
    }

    @GetMapping("/get")
    public String get(String name, Integer age) {
        return "名字：" + name + " age:" + age;
    }

    @PostMapping("/post")
    public String post(@RequestBody Person person) {
        int i = 1 / 0;
        return person.toString();
    }

    @PutMapping("/put")
    public String put(@RequestBody Person person) {
        return person.toString();
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody Person person) {
        return person.toString();
    }
}

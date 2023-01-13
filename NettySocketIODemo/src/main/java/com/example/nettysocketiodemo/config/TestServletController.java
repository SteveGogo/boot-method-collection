package com.example.nettysocketiodemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestServletController {
//	@Autowired
//	private SocketIOService socketIOService;

    @Autowired
    private TestSocketIOService testSocketIOService;

    @GetMapping("/myServlet")
    public String testMyHttpServlet(){
        return "hello servlet";
    }

    @PostMapping("/myServlet1")
    public String testMyHttpServlet1(){
        return "hello servlet";
    }

    @GetMapping("/sendMsg")
    public String sendMsg(@RequestParam("userId") String userId, @RequestParam("msg") String msg){
        return "hello socketIO";
    }

    @GetMapping("/testSendMsg")
    public String testSendMsg(@RequestParam("username") String username,@RequestParam("msg") String msg){
        Map<String, Object> map = new HashMap<>();
        map.put("msg",msg);
        testSocketIOService.sendMessage(username, map);
        return "hello socketIO";
    }
}


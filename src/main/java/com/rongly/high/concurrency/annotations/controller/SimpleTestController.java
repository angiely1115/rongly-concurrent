package com.rongly.high.concurrency.annotations.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lvrongzhuan
 * @Description:一个简单的测试controller
 * @Date: 2018/10/12 16:47
 * @Version: 1.0
 * modified by:
 */
@RestController
@RequestMapping("/simple/test")
public class SimpleTestController {
    @GetMapping("ab")
    public String testAb(){
        return "ab";
    }
}

package com.example.SpringConfigClient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RefreshScope // "POST /actuator/refresh"されると、このクラスのインスタンスが破棄される
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final String message;
    private final String common;

    // Config Serverから取得した値をコンストラクタで取得
    public HelloController(@Value("${message}") String message, @Value("${common}") String common) {
		log.info("## message: {}, common: {} ##", message, common);
    	this.message = message;
        this.common = common;

    }

    @GetMapping
    public Map<String, String> hello() {
		log.info("## message: {}, common: {} ##", message, common);
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("common", common);
        return map;
    }
}
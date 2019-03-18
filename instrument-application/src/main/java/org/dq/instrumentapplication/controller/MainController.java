package org.dq.instrumentapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {
    @GetMapping("test")
    public Map<String, Object> index() {
        Map<String, Object> map = Map.of("test", "my index test");
        return map;
    }
}

package com.example.crud_test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    @GetMapping
    public List<Map<String,String>> getList(){
        return List.of(Map.of("key","value"));
    }
}

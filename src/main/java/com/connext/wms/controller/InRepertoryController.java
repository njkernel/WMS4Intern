package com.connext.wms.controller;

import com.connext.wms.service.InRepertoryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Marcus
 * @Date: 2019/1/2 11:23
 * @Version 1.0
 */
@Controller
@RequestMapping("/inRepertory")
public class InRepertoryController {
    private final InRepertoryService inRepertoryService;

    @Autowired
    public InRepertoryController(InRepertoryService inRepertoryService) {
        this.inRepertoryService = inRepertoryService;
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("detail", inRepertoryService.findOne(id));
        return "in_repertory";
    }

    @GetMapping("page/{page}")
    public String list(@PathVariable Integer page, Model model) {
        model.addAttribute("list", inRepertoryService.findPage(page, 5));
        return "in_repertory";
    }

    @GetMapping("page/{page}/all")
    public String allList(@PathVariable Integer page, Model model) {
        model.addAttribute("list", inRepertoryService.findAllPage(page, 5));
        return "in_repertory";
    }

    @GetMapping("/search/{like}")
    public String search(@PathVariable String like, Model model) {
        String likeSth = "%" + like + "%";
        model.addAttribute("list", inRepertoryService.findAllLike(likeSth));
        return "in_repertory";
    }

    @PostMapping("/")
    public String finish(@RequestParam Integer id, @RequestParam String status, @RequestParam String list) {
        inRepertoryService.changeInRepertoryStatus(id, status);
        inRepertoryService.pushInRepertoryState(inRepertoryService.findOne(id));
        return "";
    }

    public static void main(String[] args) throws IOException {
        String s = "{\"sku\":\"111\",\"secondname\":\"111\",\"lastname\":\"111\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode ss1 = objectMapper.readTree(s);
        List<Map> maps=new ArrayList<>();
        ss1.forEach(u->maps.add((Map) u));
        maps.forEach(System.out::println);
    }
}

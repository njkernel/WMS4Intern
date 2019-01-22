package com.connext.wms.controller;

import com.connext.wms.api.dto.InRepertoryDetailDTO;
import com.connext.wms.api.util.EntityAndDto;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.util.Constant;
import com.connext.wms.util.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * @Author: Marcus
 * @Date: 2019/1/2 11:23
 * @Version 1.0
 */
@Controller
@RequestMapping("/inRepertory")
public class InRepertoryController {
    private final InRepertoryService inRepertoryService;
    private final ObjectMapper objectMapper;
    private final int SIZE = 10;

    @Autowired
    public InRepertoryController(InRepertoryService inRepertoryService, ObjectMapper objectMapper, EntityAndDto entityAndDto, Constant constant) {
        this.inRepertoryService = inRepertoryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("one", inRepertoryService.findOne(id));
        return "specific/in-detail";
    }

    @GetMapping("page/{page}")
    public String list(@PathVariable Integer page, Model model, @RequestParam(required = false, defaultValue = "") String status) {
        model.addAttribute("page", inRepertoryService.findPage(page, SIZE));
        model.addAttribute("url", "/inRepertory/page");
        return "warehouse-in-list";
    }

    @GetMapping("page/{status}/{page}")
    public String listBy(@PathVariable String status, @PathVariable Integer page, Model model) {
        model.addAttribute("page", inRepertoryService.findPageBy(status, page, SIZE));
        model.addAttribute("status", status);
        model.addAttribute("url", "/inRepertory/page/" + status);
        return "warehouse-in-list";
    }

    @GetMapping("/search/{status}/{page}")
    public String search(@PathVariable String status, @PathVariable Integer page, @RequestParam String like, Model model) {
        String likeSth = "%" + like + "%";
        model.addAttribute("page", inRepertoryService.findAllLike(status, like, page, SIZE));
        model.addAttribute("status", status);
        model.addAttribute("url", "/inRepertory/search/" + status);
        return "warehouse-in-list";
    }

    @GetMapping("/action/exception")
    public String detailAction(@RequestParam Integer id, Model model) {
        model.addAttribute("one", inRepertoryService.findOne(id));
        return "specific/in-detail-action";
    }

    @PostMapping("/action/exception")
    @ResponseBody
    public boolean finish(@RequestParam Integer id, @RequestParam String list) throws IOException {
        List<InRepertoryDetailDTO> inRepertoryDetailDTOS = objectMapper.readValue(list, new TypeReference<List<InRepertoryDetailDTO>>() {
        });
        return inRepertoryService.actionException(id, inRepertoryDetailDTOS);
    }

    @PostMapping("/action/{status}")
    @ResponseBody
    public int action(@RequestParam String list, @PathVariable String status) throws IOException {
        List<Integer> ids = objectMapper.readValue(list, new TypeReference<List<Integer>>() {
        });
        return inRepertoryService.changeStatusAndPush(ids, status);
    }
}

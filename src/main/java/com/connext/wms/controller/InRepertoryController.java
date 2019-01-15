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
import java.text.DecimalFormat;
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
    private final EntityAndDto entityAndDto;
    private final Constant constant;
    private final int SIZE = 10;

    @Autowired
    public InRepertoryController(InRepertoryService inRepertoryService, ObjectMapper objectMapper, EntityAndDto entityAndDto, Constant constant) {
        this.inRepertoryService = inRepertoryService;
        this.objectMapper = objectMapper;
        this.entityAndDto = entityAndDto;
        this.constant = constant;
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("one", inRepertoryService.findOne(id));
        return "/specific/in-detail";
    }

    @GetMapping("page/{page}")
    public String list(@PathVariable Integer page, Model model, @RequestParam(required = false, defaultValue = "") String status) {
        List<InRepertory> inRepertories = inRepertoryService.findPage(page, SIZE);
        Page pageModel = inRepertoryService.getPageInfo(page, inRepertories, status);
        model.addAttribute("page", pageModel);
        return "warehouse-in-list";
    }

    @GetMapping("page/{page}/{status}")
    public String listBy(@PathVariable Integer page, @PathVariable String status, Model model) {
        List<InRepertory> inRepertories = inRepertoryService.findPageBy(status, page, SIZE);
        Page pageModel = inRepertoryService.getPageInfo(page, inRepertories, status);
        model.addAttribute("page", pageModel);
        return "warehouse-in-list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String like, Model model) {
        String likeSth = "%" + like + "%";
        Page pageModel = inRepertoryService.getPageInfo(0, inRepertoryService.findAllLike(likeSth), "");
        model.addAttribute("page", pageModel);
        return "warehouse-in-list";
    }

    @GetMapping("/action/exception")
    public String detailAction(@RequestParam Integer id, Model model) {
        model.addAttribute("one", inRepertoryService.findOne(id));
        return "/specific/in-detail-action";
    }

    @PostMapping("/action/exception")
    @ResponseBody
    public boolean finish(@RequestParam Integer id, @RequestParam String list) throws IOException {
        List<InRepertoryDetailDTO> inRepertoryDetailDTOS = objectMapper.readValue(list, new TypeReference<List<InRepertoryDetailDTO>>() {
        });
        InRepertory inRepertory = inRepertoryService.findOne(id);
        inRepertory.setRepertoryDetails(entityAndDto.idToEntity(String.valueOf(id), inRepertoryDetailDTOS));
        boolean result = inRepertoryService.changeInRepertoryStatus(id, constant.SUCCESS_STATUS);
        if (result) {
            inRepertoryService.pushInRepertoryState(inRepertory);
            return true;
        }
        return false;
    }

    @PostMapping("/action/{status}")
    @ResponseBody
    public boolean action(@RequestParam String list, @PathVariable String status) throws IOException {
        List<Integer> ids = objectMapper.readValue(list, new TypeReference<List<Integer>>() {
        });
        ids.forEach(
                u -> {
                    inRepertoryService.changeInRepertoryStatus(u, status);
                    //inRepertoryService.pushInRepertoryState(inRepertoryService.findOne(u));
                }
        );
        return true;
    }
}

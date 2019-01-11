package com.connext.wms.controller;

<<<<<<< HEAD
<<<<<<< HEAD
import com.connext.wms.api.dto.InRepertoryDetailDTO;
import com.connext.wms.api.dto.InputSth;
import com.connext.wms.api.util.EntityAndDto;
import com.connext.wms.entity.InRepertory;
=======
>>>>>>> parent of 7aa2b13... Merge pull request #2 from njkernel/Marcus
import com.connext.wms.service.InRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.io.IOException;
import java.util.*;

=======
>>>>>>> parent of 3575d3e... 1.0 DAO、SERVICE层基本完成
=======
>>>>>>> parent of 7aa2b13... Merge pull request #2 from njkernel/Marcus
/**
 * @Author: Marcus
 * @Date: 2019/1/2 11:23
 * @Version 1.0
 */
public class InRepertoryController {
    private final InRepertoryService inRepertoryService;

    @Autowired
    public InRepertoryController(InRepertoryService inRepertoryService) {
        this.inRepertoryService = inRepertoryService;
    }
    @GetMapping("detail/{id}")
    public String detail(@PathVariable Integer id, Model model){
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
    public String search(@PathVariable String like, Model model){
        String likeSth="%"+like+"%";
        model.addAttribute("list",inRepertoryService.findAllLike(likeSth));
        return "in_repertory";
    }

    @PostMapping("/")
    public String finish(@RequestParam Integer id, @RequestParam String status, @RequestParam String list) {
        inRepertoryService.changeInRepertoryStatus(id, status);
        return "";
    }
}

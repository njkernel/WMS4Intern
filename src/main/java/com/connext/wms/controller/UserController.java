package com.connext.wms.controller;

import com.connext.wms.entity.User;
import com.connext.wms.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 9:19
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String toLogin() {
        return "login/loading";
    }

    //跳转注册
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    //注册
    @RequestMapping("/register")
    public String register(@RequestParam String telephone, @RequestParam String username, @RequestParam String password, @RequestParam String role, HttpServletRequest req) {
        User user = new User();
        password = new BCryptPasswordEncoder().encode(req.getParameter("password"));
        boolean validation = (telephone.equals("") || telephone == null) || (username.equals("") || username == null) || (password.equals("") || password == null);
        if (!validation) {
            user.setTelephone(telephone);
            user.setPassword(password);
            user.setUsername(username);
            user.setRole(role);
            userService.register(user);
        }

        return "details/user/administrator";
    }

    //验证注册
    @RequestMapping("/checkReg")
    @ResponseBody
    public Map<String, Object> checkReg(@RequestParam(value = "phone", required = false) String phone, HttpServletRequest request) {
        final Map<String, Object> result = new HashMap<>();
        request.getSession().setAttribute("phone", phone);
        if (userService.checkRegister(phone) == 0) {
            result.put("flag", true);
        } else {
            result.put("flag", false);
        }
        return result;
    }


    //用户列表
    @RequestMapping("/queryAll")
    public String queryAll(Model model) {
        List<User> userList = this.userService.queryAll();
        model.addAttribute("users", userList);
        return "details/user/administrator";
    }

    //删除用户
    @RequestMapping("/delete")
    public String delete(Integer id) {
        this.userService.delete(id);
        return "details/user/administrator";
    }

    //按序号修改
    @RequestMapping("/updateById")
    public String updateById(int id, Model model) {
        User user = this.userService.updateById(id);
        model.addAttribute("user1", user);
        model.addAttribute("userlist", userService.updateById(id));
        return "redirect:details/user/administrator";
    }

    //按序号获取用户信息
    @ResponseBody
    @RequestMapping("/getUserByID")
    public User getUserByID(int id) {
        return userService.updateById(id);
    }

    //修改用户
    @RequestMapping("/updateByPrimaryKey")
    public String updateByPrimaryKey(@RequestParam Integer id, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "telephone", required = false) String telephone, @RequestParam(value = "role", required = false) String role) {
        final User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setTelephone(telephone);
        user.setRole(role);
        userService.updateByPrimaryKey(user);
        return "details/user/administrator";
    }

    //忘记密码
    @RequestMapping("/forget")
    public String forget(@RequestParam(value = "telephone", required = false) String telephone, @RequestParam(value = "password", required = false) String password) {
        final User user = new User();
        user.setTelephone(telephone);
        user.setPassword(password);
        userService.forget(user);
        return "login";
    }
}
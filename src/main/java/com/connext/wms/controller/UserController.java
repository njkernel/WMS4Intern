package com.connext.wms.controller;

import com.connext.wms.entity.User;
import com.connext.wms.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    //登陆
    @RequestMapping("login")
    public String login(HttpServletRequest req, HttpSession httpSession, Model model) {
        String password = new BCryptPasswordEncoder().encode(req.getParameter("password"));
        Map<String, String> usermap = new HashMap<String, String>();
        usermap.put("user_name", req.getParameter("user_name"));
        usermap.put("password", password);
        User user = userService.login(usermap);
        if (user != null) {
            httpSession.setAttribute("user_name", user.getUserName());
            // 登录成功进入消息页面
            return "";
        } else {// 登录失败回到登录页面
            model.addAttribute("message", "用户名或密码输入错误,请重新输入");
            return "login";
        }
    }

    //跳转注册
    @RequestMapping("toRegister")
    public String toRegister() {
        return "register";
    }

    //注册
    @RequestMapping("/register")
    public String register(@RequestParam(value = "telephone") String telephone, @RequestParam(value = "user_name") String user_name, @RequestParam(value = "password") String password, HttpServletRequest req) {
        User user = new User();
        password = new BCryptPasswordEncoder().encode(req.getParameter("password"));
        user.setTelephone(telephone);
        user.setPassword(password);
        user.setUserName(user_name);
        userService.register(user);
        return "login";
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

    //退出登陆
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session) {
        session.invalidate();
        return "login";
    }

    //用户列表
    @RequestMapping("/queryAll")
    public ModelAndView queryAll() {
        ModelAndView mv = new ModelAndView("userlist");
        List<User> userList = this.userService.queryAll();
        mv.addObject("userList", userList);
        return mv;
    }

    //删除用户
    @RequestMapping("/delete")
    public String delete(Integer id) {
        this.userService.delete(id);
        return "redirect:/user/queryAll";
    }

    //跳转修改
    @RequestMapping("/modifyUser")
    public String modifyUser(){
        //到修改用户页面
        return "modifyUser";
    }

    //修改用户
    @RequestMapping("updateByPrimaryKey")
    @ResponseBody
    public String updateByPrimaryKey(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "password", required = false) String password,@RequestParam(value = "telephone", required = false) String telephone, @RequestParam(value = "role", required = false) String role) {
        final User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setRole(role);
        userService.updateByPrimaryKey(user);
        return "success";
    }

    //修改权限
    @RequestMapping("/modifyRole")
    @ResponseBody
    public String modifyRole(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "role", required = false) String role) {
        final User user = new User();
        user.setId(id);
        user.setRole(role);
        userService.modifyRole(user);
        return "success";
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
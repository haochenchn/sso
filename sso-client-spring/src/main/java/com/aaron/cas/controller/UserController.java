package com.aaron.cas.controller;

import com.aaron.cas.service.IUserService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("insert")
    public String insert(String username){
        userService.insert(username);
        return "result";
    }

    @RequestMapping("test")
    @ResponseBody
    public Object test(HttpServletRequest request){
        AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
        String name = principal.getName();
        Map attributes = principal.getAttributes();
        attributes.put("name", name);
        return attributes;
    }

    /**
     * 跳转到默认页面
     * @param session
     * @return
     */
    @RequestMapping("/logout1")
    public String loginOut(HttpSession session){
        session.invalidate();
        System.out.println("登出-logout1");
        //这个是直接退出，走的是默认退出方式
        return "redirect:https://server.cas.com:8443/cas/logout";
    }

    /**
     * 跳转到指定页面
     * @param session
     * @return
     */
    @RequestMapping("/logout2")
    public String loginOut2(HttpSession session){
        session.invalidate();
        System.out.println("登出-logout2");
        //退出登录后，跳转到退出成功的页面，不走默认页面
        return "redirect:https://server.cas.com:8443/cas/logout?service=http://app1.cas.com:8081";
    }

}

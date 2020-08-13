package com.fengbin.crowd.mvc.handler;
/*
@作者 冯彬
@时间 2020-06-02-23:19
*/

import com.fengbin.crowd.entity.Admin;
import com.fengbin.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap){
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList",adminList);
        Admin admin = new Admin(10, "xxx", "xxx", "xxx", "xxx@qq.com", null);
        return "target";
    }


}

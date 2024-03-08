package com.vdc.autocall.controller;


import com.vdc.autocall.common.resolver.CommandMap;
import com.vdc.autocall.service.DefaultService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DefaultController {

    @Resource(name = "defaultService")
    private DefaultService defaultService;

    @Value("${login.username}")
    private String username;
    @Value("${login.password}")
    private String password;

    @RequestMapping("/login")
    public ModelAndView page_index() {
        return new ModelAndView("view/login");
    }

    @RequestMapping("/main")
    public ModelAndView main_index() {
        return new ModelAndView("view/main");
    }

    @RequestMapping("/login/proc")
    @ResponseBody
    public Map<String, Object> loginproc(@RequestParam("emp_no") String empNo, @RequestParam("pwd") String pwd, HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        if (empNo.equals(username) && pwd.equals(password)) {
            session.setAttribute("emp_no", empNo);
            session.setAttribute("emp_nm", "테스터");
            response.put("rst", true);
        } else {
            response.put("rst", false);
        }

        return response;
    }
@RequestMapping(value = "/logout")
    public ModelAndView Logout(HttpSession session) {
        if (session.getAttribute("emp_no") != null) {
            session.invalidate(); //세션 삭제
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value="/list")
    public ModelAndView DeviceList(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("jsonView");
        List<Map<String,Object>> dvc = defaultService.DeviceList(commandMap.getMap());
        mv.addObject("data", dvc);
        return mv;
    }

    @RequestMapping(value="/add")
    public ModelAndView DeviceAdd(CommandMap commandMap, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("jsonView");
        defaultService.addDevice(commandMap.getMap());
        return mv;
    }

    @RequestMapping(value="/edit")
    public ModelAndView DeviceEdit(CommandMap commandMap, HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView("jsonView");
        defaultService.editDevice(commandMap.getMap());
        return mv;
    }

    @RequestMapping(value="/del")
    public ModelAndView DeviceDel(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("jsonView");
        defaultService.delDevice(commandMap.getMap());
        return mv;
    }
}


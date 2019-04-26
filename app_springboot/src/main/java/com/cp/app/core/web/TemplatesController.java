package com.cp.app.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName TemplatesController
 * @Description TODO 页面跳转
 * @createdate 2019/2/14 星期四 15:25
 */
@Controller
@RequestMapping("api")
public class TemplatesController {
    @GetMapping("/login")
    public String pageLogin() {
        return "/login";
    }
}

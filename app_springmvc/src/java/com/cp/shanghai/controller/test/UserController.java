package com.cp.shanghai.controller.test;

import com.cp.shanghai.controller.BaseController;
import com.cp.shanghai.service.Login;
import com.cp.shanghai.util.exception.BussinessException;
import com.cp.shanghai.util.exception.ExceptionMessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName TestController
 * @Description TODO 测试类
 * @createdate 2018/9/19 星期三 15:21
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private Login login;

    @RequestMapping(value = "api/test", method = RequestMethod.GET)
    public void test(@RequestParam("") String param, HttpServletResponse response) throws Exception {
        returnJsonResponse(param, response);
    }

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("") String account, @RequestParam("") String password) throws Exception {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password))
            BussinessException.throwExcption(ExceptionMessageEnum.EM_LOGIN_FAIL);
        return returnSucceedJsonResponse(login.login(account, password));
    }


    @RequestMapping(value = "api/{account}/logint", method = RequestMethod.GET)
    @ResponseBody
    public String logint(@PathVariable("account") String account, @RequestParam("") String password) throws Exception {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password))
            BussinessException.throwExcption(ExceptionMessageEnum.EM_LOGIN_FAIL);
        return returnSucceedJsonResponse(login.login(account, password));
    }

    List<String> list = new ArrayList<>();

    @RequestMapping(value = "api/main", method = RequestMethod.GET)
    public ModelAndView main(HttpServletResponse response) throws Exception {
        throw new Exception();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        Map<String,Object> map = new HashMap<>();
//        map.put("data",list);
//        modelAndView.addAllObjects(map);
//        return modelAndView;
    }

    @RequestMapping(value = "api/select", method = RequestMethod.GET)
    public ModelAndView select(HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        Map<String, Object> map = new HashMap<>();

        map.put("data", list);
        int n = new Random().nextInt(list.size());
        String select = list.get(n);
        map.put("selectValue", select);

        modelAndView.addAllObjects(map);
        return modelAndView;
    }
}

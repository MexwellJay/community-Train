package study.mexwelljay.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther Jay
 * @date 2019/11/6
 * 模块概述：码啾社区首页
 **/

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}

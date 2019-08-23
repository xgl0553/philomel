package com.qinjian.philomel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/server")
public class ServerManagerController {

    @RequestMapping("/index")
    public String index(){
        return "serverManager/index";
    }
}

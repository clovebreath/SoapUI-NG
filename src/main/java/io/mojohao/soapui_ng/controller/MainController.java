package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {
    @RequestMapping(value = "/error")
    public String error(){
        return "error";
    }
}

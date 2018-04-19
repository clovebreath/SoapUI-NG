package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/countAll")
    public List<ChartTypeDto> countAll(){
        return commonService.countAll();
    }
}

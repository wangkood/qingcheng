package cn.wangxing.qing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToPageController {


    @GetMapping("/brand")
    public String toBrandPage(){
        return "goods/brand";
    }

    @GetMapping("/template")
    public String toTemplatePage(){
        return "goods/template";
    }

}

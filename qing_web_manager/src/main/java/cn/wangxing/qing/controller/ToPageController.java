package cn.wangxing.qing.controller;

import cn.wangxing.qing.pojo.other.SwapData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ToPageController {

    @GetMapping({"/","/index","/main"})
    public String toIndexPage(){
        return "main";
    }

    @GetMapping("/login")
    public String toLoginPage(){
        return "login";
    }



    @GetMapping("/returnLoginError")
    @ResponseBody
    public SwapData<Object> returnError(){
        return new SwapData<>("400","密码或用户名错误",null,null);
    }


    @GetMapping("/brand")
    public String toBrandPage(){
        return "goods/brand";
    }

    @GetMapping("/template")
    public String toTemplatePage(){
        return "goods/template";
    }

    @GetMapping("/goods")
    public String toGoodsPage(){
        return "goods/goods";
    }

}

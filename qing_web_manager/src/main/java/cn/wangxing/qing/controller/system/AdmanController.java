package cn.wangxing.qing.controller.system;


import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.system.Admin;
import cn.wangxing.qing.service.system.AdminService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin")
public class AdmanController extends BaseController<Admin> {

    @Reference
    AdminService adminService;


    @RequestMapping("/hello")
    public String hello(){
        System.out.println(adminService);
        return "hello";
    }

}

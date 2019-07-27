package cn.wangxing.qing.controller.system;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.system.Role;
import cn.wangxing.qing.service.system.RoleService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/role")
public class RoleController extends BaseController<Role> {

    @Reference
    private RoleService roleService;



}

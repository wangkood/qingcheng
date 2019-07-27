package cn.wangxing.qing.controller.user;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.user.User;
import cn.wangxing.qing.service.user.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController<User> {

    @Reference
    private UserService userService;



}

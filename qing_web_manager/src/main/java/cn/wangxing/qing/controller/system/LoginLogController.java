package cn.wangxing.qing.controller.system;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.system.LoginLog;
import cn.wangxing.qing.service.system.LoginLogService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/loginLog")
public class LoginLogController extends BaseController<LoginLog> {


    @Reference
    LoginLogService loginLogService;

}

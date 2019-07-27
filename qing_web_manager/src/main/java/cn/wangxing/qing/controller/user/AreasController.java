package cn.wangxing.qing.controller.user;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.user.Areas;
import cn.wangxing.qing.service.user.AreasService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/areas")
public class AreasController extends BaseController<Areas> {

    @Reference
    private AreasService areasService;

}

package cn.wangxing.qing.controller.system;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.system.Resource;
import cn.wangxing.qing.service.system.ResourceService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/resource")
public class ResourceController extends BaseController<Resource> {

    @Reference
    private ResourceService resourceService;

}

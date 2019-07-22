package cn.wangxing.qing.controller.goods;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.goods.Template;
import cn.wangxing.qing.service.goods.TemplateService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/template")
public class TemplateController extends BaseController<Template> {

    @Reference
    TemplateService templateService;


}

package cn.wangxing.qing.controller.goods;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.goods.Spec;
import cn.wangxing.qing.service.goods.SpecService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/spec")
public class SpecController extends BaseController<Spec> {

    @Reference
    SpecService specService;


}

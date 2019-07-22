package cn.wangxing.qing.controller.goods;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.goods.Para;
import cn.wangxing.qing.service.goods.ParaService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/para")
public class ParaController extends BaseController<Para> {

    @Reference
    ParaService paraService;


}

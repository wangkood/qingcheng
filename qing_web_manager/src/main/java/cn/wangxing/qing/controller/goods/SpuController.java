package cn.wangxing.qing.controller.goods;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.goods.Spu;
import cn.wangxing.qing.service.goods.SpuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品信息
 */
@RestController
@RequestMapping("/api/v1/sku")
public class SpuController extends BaseController<Spu> {

    @Reference
    SpuService spuService;

}

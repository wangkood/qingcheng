package cn.wangxing.qing.controller.goods;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.goods.Sku;
import cn.wangxing.qing.service.goods.SkuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存信息
 */
@RestController
@RequestMapping("/api/v1/sku")
public class SkuController extends BaseController<Sku> {

    @Reference
    SkuService skuService;

}

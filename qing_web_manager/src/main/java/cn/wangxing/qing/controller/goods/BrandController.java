package cn.wangxing.qing.controller.goods;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.goods.Brand;
import cn.wangxing.qing.service.goods.BrandService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 品牌管理
 */
@RestController
@RequestMapping("/api/v1/brand")
public class BrandController extends BaseController<Brand> {

    @Reference
    BrandService brandService;

}

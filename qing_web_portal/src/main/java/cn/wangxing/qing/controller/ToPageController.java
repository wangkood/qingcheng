package cn.wangxing.qing.controller;

import cn.wangxing.qing.pojo.business.Ad;
import cn.wangxing.qing.pojo.goods.Category;
import cn.wangxing.qing.pojo.goods.Sku;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.business.AdService;
import cn.wangxing.qing.service.goods.CategoryService;
import cn.wangxing.qing.service.goods.SkuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ToPageController {

    @Reference
    CategoryService categoryService;

    @Reference
    SkuService skuService;

    @Reference
    AdService adService;



    // 首页
    @GetMapping({"/","/index"})
    public String toIndexPage(ModelMap map){

        // 查询 分类数据
        SwapData<List<Category>> treeListSwapData = categoryService.treelist(0L);
        if(!SwapData.SUCCESS_CODE.equals(treeListSwapData.getErrorCode())){
            return "500";
        }
        map.addAttribute("categoryList",treeListSwapData.getObj());

        // 轮播图
        SwapData<List<Ad>> adSwapData = adService.selectAll();
        if (!SwapData.SUCCESS_CODE.equals(adSwapData.getErrorCode())){
            return "500";
        }
        map.addAttribute("adList", adSwapData.getObj());

        return "index";
    }

    // 商品详情
    @GetMapping({"/goods/{id}"})
    public String toItemPage(@PathVariable long id, ModelMap map){

        SwapData<Sku> skuSwapData = skuService.getByLongId(id);
        if (!skuSwapData.getErrorCode().equals(SwapData.SUCCESS_CODE)){
            return "404";
        }

        map.addAttribute("sku", skuSwapData.getObj());
        return "item";
    }

}

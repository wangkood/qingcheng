package cn.wangxing.qing.controller.goods;

import cn.wangxing.qing.pojo.goods.Goods;
import cn.wangxing.qing.pojo.other.PageInfo;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.goods.GoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/goods")
public class GoodsController  {

    @Reference
    GoodsService goodsService;

    @PostMapping("/insert_one")
    public SwapData<Goods> insert(@RequestBody Goods goods) {
        return null;
    }

    @PostMapping("/delete_one")
    public SwapData<Goods> delete(@RequestBody Long id) {
        return null;
    }


    @PostMapping("/update_one")
    public SwapData<Goods> update(@RequestBody Goods goods) {
        return null;
    }


    @PostMapping("/select_one")
    public SwapData<Goods> selectById(@RequestBody Long id) {

        return goodsService.selectById(id);
    }

    @PostMapping("/select_page")
    public SwapData<Goods> select(@RequestBody PageInfo<Goods> pageInfo) {

        return goodsService.select(pageInfo);
    }
}

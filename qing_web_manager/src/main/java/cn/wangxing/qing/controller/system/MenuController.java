package cn.wangxing.qing.controller.system;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.pojo.system.Menu;
import cn.wangxing.qing.service.system.MenuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/menu")
public class MenuController extends BaseController<Menu> {

    @Reference
    private MenuService menuService;

    @RequestMapping("/selectAll")
    public SwapData<Menu> selectAll(){
        return menuService.selectAll();
    }



}

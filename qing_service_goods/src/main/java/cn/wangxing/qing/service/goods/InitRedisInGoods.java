package cn.wangxing.qing.service.goods;

import cn.wangxing.qing.pojo.goods.Category;
import cn.wangxing.qing.pojo.other.SwapData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 在项目启动时 从数据库查询数据 填充缓存
 */
@Component
public class InitRedisInGoods implements InitializingBean {

    @Autowired
    CategoryService categoryService;

    @Override
    public void afterPropertiesSet(){

        SwapData<List<Category>> treeListSwapData = categoryService.treelist(0L);
        if(SwapData.SUCCESS_CODE.equals(treeListSwapData.getErrorCode())){
            System.out.println("分类数据初始化成功");
        }else {
            System.out.println("分类数据初始化失败");
        }
    }
}

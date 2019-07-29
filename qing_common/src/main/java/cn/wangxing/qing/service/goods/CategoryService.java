package cn.wangxing.qing.service.goods;

import cn.wangxing.qing.pojo.goods.Category;
import cn.wangxing.qing.pojo.other.SwapData;

import java.util.List;

/**
 * category业务逻辑层
 */
public interface CategoryService {

    // 获取所有
    SwapData<List<Category>> treelist(long parentId);
}

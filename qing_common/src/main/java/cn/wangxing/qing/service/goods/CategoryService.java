package cn.wangxing.qing.service.goods;

import cn.wangxing.qing.pojo.goods.Category;

import java.util.List;

/**
 * category业务逻辑层
 */
public interface CategoryService {

    // 获取所有
    List<Category> list(long parentId);
}

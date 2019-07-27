package cn.wangxing.qing.service.goods;

import cn.wangxing.qing.pojo.goods.Sku;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.BaseService;

/**
 * sku业务逻辑层
 */
public interface SkuService extends BaseService<Sku> {
    SwapData<Sku> getByLongId(long id);
}

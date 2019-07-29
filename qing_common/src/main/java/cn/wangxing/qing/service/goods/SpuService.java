package cn.wangxing.qing.service.goods;


import cn.wangxing.qing.pojo.goods.Spu;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.BaseService;

/**
 * spu业务逻辑层
 */
public interface SpuService extends BaseService<Spu> {


    SwapData<Spu> getByLongId(long id);
}

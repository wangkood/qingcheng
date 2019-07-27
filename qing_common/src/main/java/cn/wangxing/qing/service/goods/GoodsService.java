package cn.wangxing.qing.service.goods;


import cn.wangxing.qing.pojo.goods.Goods;
import cn.wangxing.qing.pojo.other.PageInfo;
import cn.wangxing.qing.pojo.other.SwapData;

public interface GoodsService {

    SwapData<Goods> insert(Goods goods);
    SwapData<Goods> delete(Long id);
    SwapData<Goods> update(Goods goods);
    SwapData<Goods> selectById(Long id);
    SwapData<Goods> select(PageInfo<Goods> pageInfo);

}

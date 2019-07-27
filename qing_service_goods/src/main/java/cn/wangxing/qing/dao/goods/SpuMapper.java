package cn.wangxing.qing.dao.goods;

import cn.wangxing.qing.pojo.goods.Spu;
import tk.mybatis.mapper.common.Mapper;

public interface SpuMapper extends Mapper<Spu> {

    Spu getById(long id);

}

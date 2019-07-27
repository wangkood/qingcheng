package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.SpuMapper;
import cn.wangxing.qing.pojo.goods.Spu;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.goods.SpuService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = SpuService.class)
public class SpuServiceImpl extends BaseServiceImpl<Spu> implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

}

package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.SkuMapper;
import cn.wangxing.qing.dao.goods.SpuMapper;
import cn.wangxing.qing.pojo.goods.Sku;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.goods.SkuService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service(interfaceClass = SkuService.class)
public class SkuServiceImpl extends BaseServiceImpl<Sku> implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SpuMapper spuMapper;


    public SwapData<Sku> getByLongId(long id){
        try {

            Sku sku = skuMapper.getById(id);
            if (sku == null) {
                rollback();
                return new SwapData<Sku>("400", "没有查到对象", null);
            }

            sku.setSpu(spuMapper.getById( Long.parseLong(sku.getSpuId())));

            return new SwapData<>(SwapData.SUCCESS_CODE, "success", sku);

        } catch (Exception e) {
            rollback();
            return new SwapData<>(SwapData.EXCEPTION_CODE,e.getMessage());
        }
    }

}

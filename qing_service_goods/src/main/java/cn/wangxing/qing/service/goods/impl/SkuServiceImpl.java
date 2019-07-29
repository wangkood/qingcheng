package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.SkuMapper;
import cn.wangxing.qing.pojo.goods.Sku;
import cn.wangxing.qing.pojo.goods.Spu;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.goods.SkuService;
import cn.wangxing.qing.service.goods.SpuService;
import cn.wangxing.qing.util.RedisUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


@Service(interfaceClass = SkuService.class)
public class SkuServiceImpl extends BaseServiceImpl<Sku> implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SpuService spuService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 更具id获取 sku 完整
     *  意外情况
     *      1，
     */
    @Override
    public SwapData<Sku> getByLongId(long id){
        try {

            // 找到sku
            Sku sku = (Sku)redisTemplate.boundHashOps(RedisUtils.Keys.SKU_HASH_KEY).get(id); //从缓存中找
            if ( sku == null){
                // 数据库中找
                sku = skuMapper.getById(id);
                if (sku == null) {
                    redisTemplate.boundHashOps(RedisUtils.Keys.SKU_HASH_KEY).put(sku.getId(),0); // 防止 缓存穿透 下次获取数据库未有值会导致类型转换异常
                    throw new RuntimeException("can`t found sku by id");
                }else {
                    redisTemplate.boundHashOps(RedisUtils.Keys.SKU_HASH_KEY).put(sku.getId(),sku);
                }
            }

            // 找到spu
            SwapData<Spu> spuSwapData = spuService.getByLongId(sku.getSpuId());
            if (!SwapData.SUCCESS_CODE.equals(spuSwapData.getErrorCode())){
                throw new RuntimeException("can`t found spu by id");
            }

            sku.setSpu(spuSwapData.getObj());
            return new SwapData<>(SwapData.SUCCESS_CODE, "success", sku);

        } catch (Exception e) {
            rollback();
            return new SwapData<>(SwapData.EXCEPTION_CODE,e.getMessage());
        }
    }



}

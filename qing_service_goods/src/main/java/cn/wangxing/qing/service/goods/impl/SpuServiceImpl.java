package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.SpuMapper;
import cn.wangxing.qing.pojo.goods.Spu;
import cn.wangxing.qing.pojo.goods.Template;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.goods.SpecService;
import cn.wangxing.qing.service.goods.SpuService;
import cn.wangxing.qing.service.goods.TemplateService;
import cn.wangxing.qing.util.RedisUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Service(interfaceClass = SpuService.class)
public class SpuServiceImpl extends BaseServiceImpl<Spu> implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 更具 id 查找 spu 有redis缓存
     */
    @Override
    public SwapData<Spu> getByLongId(long id){
        try {
            // 找到spu
            Spu spu = (Spu) redisTemplate.boundHashOps(RedisUtils.Keys.SPU_HASH_KEY).get(id); //从缓存中找
            if ( spu == null){
                // 数据库中找
                spu = spuMapper.getById(id);
                if (spu == null) {
                    redisTemplate.boundHashOps(RedisUtils.Keys.SPU_HASH_KEY).put(spu.getId(),0); // 防止 缓存穿透
                    throw new RuntimeException("can`t found spu by id");
                }else {
                    // 找到模板
                    SwapData<Template> swapData = templateService.selectById(spu.getTemplateId());
                    if(SwapData.SUCCESS_CODE.equals(swapData.getErrorCode())){
                        spu.setTemplate(swapData.getObj());
                    }
                    redisTemplate.boundHashOps(RedisUtils.Keys.SPU_HASH_KEY).put(spu.getId(),spu);
                }
            }

            return new SwapData<>(SwapData.SUCCESS_CODE, "", spu);

        } catch (Exception e) {
            rollback();
            return new SwapData<>(SwapData.EXCEPTION_CODE,e.getMessage());
        }
    }

}

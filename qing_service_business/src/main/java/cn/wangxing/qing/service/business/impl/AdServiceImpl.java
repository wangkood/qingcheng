package cn.wangxing.qing.service.business.impl;
import cn.wangxing.qing.dao.business.AdMapper;
import cn.wangxing.qing.pojo.business.Ad;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.business.AdService;
import cn.wangxing.qing.util.RedisUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Service(interfaceClass = AdService.class)
public class AdServiceImpl extends BaseServiceImpl<Ad> implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public SwapData<List<Ad>> selectAll() {

        try{
            List<Ad> ads = (List<Ad>)redisTemplate.boundValueOps(RedisUtils.Keys.AD_KEY).get();
            if (ads == null){
                ads = adMapper.selectAll();
                if(ads != null){
                    redisTemplate.boundValueOps(RedisUtils.Keys.AD_KEY).set(ads);
                }
            }
            return new SwapData<>(SwapData.SUCCESS_CODE,"",ads);
        }catch (Exception e){
            return new SwapData<>(SwapData.EXCEPTION_CODE, e.getMessage());
        }
    }
}

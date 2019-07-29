package cn.wangxing.qing.service.business;

import cn.wangxing.qing.pojo.business.Ad;
import cn.wangxing.qing.pojo.other.SwapData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 在项目启动时 从数据库查询数据 填充缓存
 */
@Component
public class InitRedisInBusiness implements InitializingBean {

    @Autowired
    AdService adService;

    @Override
    public void afterPropertiesSet(){

        SwapData<List<Ad>> adSwapData = adService.selectAll();
        if(SwapData.SUCCESS_CODE.equals(adSwapData.getErrorCode())){
            System.out.println("广告数据初始化成功");
        }else {
            System.out.println("广告数据初始化失败");
        }

    }
}

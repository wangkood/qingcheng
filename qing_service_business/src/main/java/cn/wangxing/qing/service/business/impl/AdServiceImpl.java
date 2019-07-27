package cn.wangxing.qing.service.business.impl;
import cn.wangxing.qing.dao.business.AdMapper;
import cn.wangxing.qing.pojo.business.Ad;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.business.AdService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = AdService.class)
public class AdServiceImpl extends BaseServiceImpl<Ad> implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Override
    public List<Ad> selectAll() {
        List<Ad> ads = adMapper.selectAll();

       return ads;
    }
}

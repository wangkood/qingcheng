package cn.wangxing.qing.service.business.impl;
import cn.wangxing.qing.dao.ProvincesMapper;
import cn.wangxing.qing.pojo.user.Provinces;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.user.ProvincesService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service(interfaceClass = ProvincesService.class)
public class ProvincesServiceImpl extends BaseServiceImpl<Provinces> implements ProvincesService {

    @Autowired
    private ProvincesMapper provincesMapper;


}

package cn.wangxing.qing.service.business.impl;
import cn.wangxing.qing.dao.CitiesMapper;
import cn.wangxing.qing.pojo.user.Cities;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.user.CitiesService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = CitiesServiceImpl.class)
public class CitiesServiceImpl extends BaseServiceImpl<Cities> implements CitiesService {

    @Autowired
    private CitiesMapper citiesMapper;


}

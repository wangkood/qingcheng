package cn.wangxing.qing.service.business.impl;
import cn.wangxing.qing.dao.AddressMapper;
import cn.wangxing.qing.pojo.user.Address;
import cn.wangxing.qing.service.base.BaseService;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.user.AddressService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = AddressService.class)
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService, BaseService<Address> {

    @Autowired
    private AddressMapper addressMapper;



}

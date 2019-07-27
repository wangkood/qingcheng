package cn.wangxing.qing.service.system;
import cn.wangxing.qing.dao.system.ResourceMapper;
import cn.wangxing.qing.pojo.system.Resource;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = ResourceService.class)
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;


}

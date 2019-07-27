package cn.wangxing.qing.service.business.impl;
import cn.wangxing.qing.dao.AreasMapper;
import cn.wangxing.qing.pojo.user.Areas;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.user.AreasService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service(interfaceClass = AreasService.class)
public class AreasServiceImpl extends BaseServiceImpl<Areas> implements AreasService {

    @Autowired
    private AreasMapper areasMapper;


}

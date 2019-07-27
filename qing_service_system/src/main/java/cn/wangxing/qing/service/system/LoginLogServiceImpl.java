package cn.wangxing.qing.service.system;
import cn.wangxing.qing.dao.system.LoginLogMapper;
import cn.wangxing.qing.pojo.system.LoginLog;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = LoginLogService.class)
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog> implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;
}
